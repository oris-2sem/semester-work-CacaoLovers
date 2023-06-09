package ru.itis.pethome.security.exception;


public class AuthenticationError extends RuntimeException{

    public AuthenticationError(){
        super("Не удалось выполнить аутентификацию пользователя");
    }
}
