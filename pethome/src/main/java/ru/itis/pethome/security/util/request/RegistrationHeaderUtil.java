package ru.itis.pethome.security.util.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.pethome.dto.request.AccountRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@AllArgsConstructor
public class RegistrationHeaderUtil {

    private final ObjectMapper objectMapper;

    public boolean verify(HttpServletRequest request){
        if(request.getHeader("Content-type") == null
                || !request.getHeader("Content-type").equals("application/json")) return false;
        return true;
    }

    public AccountRequest getAccountRequest(HttpServletRequest request){
        try {
            return objectMapper.readValue(request.getReader(), AccountRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
