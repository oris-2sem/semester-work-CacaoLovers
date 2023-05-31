package ru.itis.pethome.exception;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

public class UserNotFoundException extends ContentNotFoundException{
    public UserNotFoundException(UUID uuid){
        super("User with id " + uuid.toString() + " not found");
    }
}
