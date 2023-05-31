package ru.itis.pethome.security.provider;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.itis.pethome.security.authentication.RefreshTokenAuthentication;
import ru.itis.pethome.security.util.jwt.JwtAuthUtil;


@Component
@RequiredArgsConstructor
public class RefreshTokenAuthenticationProvider implements AuthenticationProvider {
    private final JwtAuthUtil jwtAuthUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String refreshToken = authentication.getCredentials().toString();
        try {
            return jwtAuthUtil.buildAuthentication(refreshToken);
        } catch (JWTVerificationException e){
            throw new AccountExpiredException("");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return RefreshTokenAuthentication.class.isAssignableFrom(authentication);
    }
}
