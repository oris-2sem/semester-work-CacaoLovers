package ru.itis.pethome.controller.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
@Slf4j
public class ErrorMvcController {
    @GetMapping
    public String errorMvcHandler(){
        return "error";
    }
}
