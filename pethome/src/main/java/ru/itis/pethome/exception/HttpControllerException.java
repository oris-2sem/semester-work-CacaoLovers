package ru.itis.pethome.exception;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@RequiredArgsConstructor
public class HttpControllerException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public HttpControllerException(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }
}
