package ru.itis.pethome.controller.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.pethome.service.AccountService;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/mvc/account")
@RequiredArgsConstructor
public class AccountMvcController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public String profile(@PathVariable UUID id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", accountService.getAccountById(id));
        return "profile";
    }

    @GetMapping
    public String profileYourself(Principal principal){
        return "profile";
    }

    @GetMapping("/edit")
    public String editProfile(Principal principal){
        return "profile";
    }

    @PostMapping("/edit")
    public String editProfileSave(){
        return "redirect:/mvc/account";
    }

}
