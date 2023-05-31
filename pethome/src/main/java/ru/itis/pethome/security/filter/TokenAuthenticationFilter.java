package ru.itis.pethome.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import ru.itis.pethome.dto.request.AccountRequest;
import ru.itis.pethome.security.authentication.RefreshTokenAuthentication;
import ru.itis.pethome.security.detail.AccountDetail;
import ru.itis.pethome.security.util.jwt.JwtAuthUtil;
import ru.itis.pethome.security.util.request.AuthorizationHeaderUtil;
import ru.itis.pethome.security.util.request.RegistrationHeaderUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@Component
public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final String AUTH_URL = "/auth/token";
    private final ObjectMapper objectMapper;

    private final JwtAuthUtil jwtAuthUtil;

    private final AuthorizationHeaderUtil headerUtil;
    private final RegistrationHeaderUtil registrationHeaderUtil;

    public TokenAuthenticationFilter(ObjectMapper objectMapper,
                                     AuthenticationConfiguration authenticationConfiguration,
                                     JwtAuthUtil jwtAuthUtil,
                                     AuthorizationHeaderUtil headerUtil,
                                     RegistrationHeaderUtil registrationHeaderUtil) throws Exception {
        super(authenticationConfiguration.getAuthenticationManager());
        super.setFilterProcessesUrl(AUTH_URL);
        this.objectMapper = objectMapper;
        this.jwtAuthUtil = jwtAuthUtil;
        this.headerUtil = headerUtil;
        this.registrationHeaderUtil = registrationHeaderUtil;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (headerUtil.hasSessionAuthorizationToken(request)){
            String refreshToken = headerUtil.getSessionToken(request);
            RefreshTokenAuthentication tokenAuthentication = new RefreshTokenAuthentication(refreshToken);
            return super.getAuthenticationManager().authenticate(tokenAuthentication);
        } else if (registrationHeaderUtil.verify(request)){
            AccountRequest accountRequest = registrationHeaderUtil.getAccountRequest(request);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(accountRequest.getUsername(), accountRequest.getPassword());
            return super.getAuthenticationManager().authenticate(authentication);
        }else {
            return super.attemptAuthentication(request, response);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        response.setContentType("application/json");

        String username = ((AccountDetail)authResult.getPrincipal()).getUsername();
        String role = authResult.getAuthorities().stream().findFirst().orElseThrow().getAuthority();
        String issuer = request.getRequestURL().toString();
        String id = ((AccountDetail)authResult.getPrincipal()).getId();

        Map<String, String> tokens = jwtAuthUtil.generateToken(username, role, issuer, id);

        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        Cookie cookie = new Cookie("refresh", tokens.get("refreshToken"));
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*24*30); //30 DAY
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setStatus(HttpServletResponse.SC_OK);
        objectMapper.writeValue(response.getOutputStream(), tokens);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        Cookie cookie = new Cookie("refresh", "");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
