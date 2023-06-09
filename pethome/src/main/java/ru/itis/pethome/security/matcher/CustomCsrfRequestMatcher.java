package ru.itis.pethome.security.matcher;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class CustomCsrfRequestMatcher implements RequestMatcher{

    private static final Pattern CSRF_ACCEPT_PATHS = Pattern.compile("/mvc/admin/.*");


    @Override
    public boolean matches(HttpServletRequest request) {
        if (CSRF_ACCEPT_PATHS.matcher(request.getRequestURI()).matches()) return true;
        return false;
    }
}
