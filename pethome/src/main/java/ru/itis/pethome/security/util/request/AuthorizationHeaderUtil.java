package ru.itis.pethome.security.util.request;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;


@Component
public class AuthorizationHeaderUtil {
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    private static final String BEARER = "Bearer ";

    public boolean hasAuthorizationToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER_NAME);
        return header != null && header.startsWith(BEARER);
    }

    public boolean hasSessionAuthorizationToken(HttpServletRequest request) {
        if (request.getCookies() == null) return false;
        return Arrays.stream(request.getCookies()).anyMatch((cookie) -> cookie.getName().equals("refresh"));
    }

    public String getToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER_NAME);
        return authorizationHeader.substring(BEARER.length());
    }

    public String getSessionToken(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refresh"))
                .map(cookie -> cookie.getValue())
                .findFirst().get();
    }

}
