package ru.itis.pethome.security.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.itis.pethome.model.Account;
import ru.itis.pethome.security.detail.AccountDetail;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Component
public class JwtAuthUtil {

    private final static long ACCESS_TOKEN_EXPIRE = 1000*60*15;
    private final static long REFRESH_TOKEN_EXPIRE = 1000*60*60*24;


    public Map<String, String> generateToken(String subject, String authority, String issue, String id){
        Algorithm algorithm = Algorithm.HMAC256("secret");

        String accessToken = JWT.create()
                .withSubject(subject)
                .withIssuer(issue)
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE))
                .withClaim("role", authority)
                .withClaim("uuid", id)
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(subject)
                .withIssuer(issue)
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE))
                .withClaim("role", authority)
                .withClaim("uuid", id)
                .sign(algorithm);

        return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
    }

    public Authentication buildAuthentication(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256("secret");

        JWTVerifier jwtVerifier = JWT.require(algorithm).build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        String username = decodedJWT.getSubject();
        String role = decodedJWT.getClaim("role").asString();
        String issuer = decodedJWT.getIssuer();

        return new UsernamePasswordAuthenticationToken(new AccountDetail(
                        Account.builder()
                                .username(username)
                                .role(Account.Role.valueOf(role))
                                .build()
                        ),
                null, Collections.singleton(new SimpleGrantedAuthority(role)));
    }
}
