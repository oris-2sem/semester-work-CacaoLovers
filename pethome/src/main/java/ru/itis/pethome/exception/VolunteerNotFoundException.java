package ru.itis.pethome.exception;

public class VolunteerNotFoundException extends ContentNotFoundException{
    public VolunteerNotFoundException(String username){
        super("Volunteer " + username + " not found");
    }
}
