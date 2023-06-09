package ru.itis.pethome.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home")
@Controller
public class HomePageMvcController {
    @GetMapping
    public String getHomePage(){
        return "index";
    }
}
