package ru.itis.pethome.controller.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.pethome.controller.exception.ExceptionMessage;
import ru.itis.pethome.exception.ContentNotFoundException;
import ru.itis.pethome.exception.HttpControllerException;

@ControllerAdvice
public class HttpRestControllerHandler {

    @ExceptionHandler(ContentNotFoundException.class)
    public final ResponseEntity<ExceptionMessage> handleContentNotFound(ContentNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ExceptionMessage.builder()
                    .name(ex.getClass().getName())
                    .code(ex.getStatus().value())
                    .error(ex.getMessage())
                    .build()
                );
    }

    @ExceptionHandler(HttpControllerException.class)
    public final ResponseEntity<ExceptionMessage> handleHttpException(HttpControllerException ex){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ExceptionMessage.builder()
                        .name(ex.getClass().getName())
                        .code(ex.getStatus().value())
                        .error(ex.getMessage())
                        .build()
                );
    }

}
