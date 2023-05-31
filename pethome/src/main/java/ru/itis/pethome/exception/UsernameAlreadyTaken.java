package ru.itis.pethome.exception;


import org.springframework.http.HttpStatus;

public class UsernameAlreadyTaken extends HttpControllerException{
    public UsernameAlreadyTaken(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
