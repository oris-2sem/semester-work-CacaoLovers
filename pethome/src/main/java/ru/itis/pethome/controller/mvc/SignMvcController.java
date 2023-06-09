package ru.itis.pethome.controller.mvc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.pethome.dto.request.AccountRequest;
import ru.itis.pethome.dto.request.SignUpRequest;
import ru.itis.pethome.service.AccountService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/mvc/auth")
@AllArgsConstructor
public class SignMvcController {

    private final AccountService accountService;

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("signUpRequest", new SignUpRequest());

        return "login";
    }


    @PostMapping("/login")
    public String login(BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "login";
        }

        return "redirect:/mvc/account/profile";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model){

        model.addAttribute("signUpRequest", new SignUpRequest());


        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid SignUpRequest signUpRequest,
                               BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "registration";
        }

        accountService.signUp(signUpRequest);

        return "login";
    }

}
