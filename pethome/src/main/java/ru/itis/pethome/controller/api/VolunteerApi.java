package ru.itis.pethome.controller.api;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = {RequestMethod.POST})
public interface VolunteerApi {

}
