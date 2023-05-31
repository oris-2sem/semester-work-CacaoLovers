package ru.itis.pethome.exception;

import lombok.Builder;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

public class ContentNotFoundException extends HttpControllerException{

    public ContentNotFoundException(){
        super(HttpStatus.NOT_FOUND, "Content hasn't found");
    }

    public ContentNotFoundException(String message){
        super(HttpStatus.NOT_FOUND, message);
    }
}
