package ru.itis.pethome.exception;


public class DistrictNotFoundException extends ContentNotFoundException{
    public DistrictNotFoundException(String name){
        super("District " + name + " not found");
    }
}
