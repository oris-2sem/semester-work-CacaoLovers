package ru.itis.pethome.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.pethome.security.util.jwt.JwtAuthUtil;
import ru.itis.pethome.security.util.request.AuthorizationHeaderUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@AllArgsConstructor
public class TokenAuthorizationFilter extends OncePerRequestFilter {
    private final String AUTH_URL = "/auth/token";
    private final AuthorizationHeaderUtil headerUtil;
    private final JwtAuthUtil jwtAuthUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals(AUTH_URL)){
            filterChain.doFilter(request, response);
        } else {
            if (headerUtil.hasAuthorizationToken(request)) {
                String token = headerUtil.getToken(request);
                try {
                    Authentication authentication = jwtAuthUtil.buildAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                } catch (JWTVerificationException e) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
