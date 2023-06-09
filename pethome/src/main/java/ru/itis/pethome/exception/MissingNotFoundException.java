package ru.itis.pethome.exception;

import java.util.UUID;

public class MissingNotFoundException extends ContentNotFoundException{

    public MissingNotFoundException(UUID uuid){
        super("Missing with id " + uuid + " not found");
    }

}
