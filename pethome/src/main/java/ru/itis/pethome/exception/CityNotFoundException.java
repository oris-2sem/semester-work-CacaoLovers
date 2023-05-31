package ru.itis.pethome.exception;

public class CityNotFoundException extends ContentNotFoundException{
    public CityNotFoundException(String name){
        super("City " + name + " not found");
    }
}
