package ru.itis.pethome.controller.mvc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.pethome.dto.response.AccountResponse;
import ru.itis.pethome.model.Account;
import ru.itis.pethome.service.AccountService;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/mvc/account")
@AllArgsConstructor
public class AccountMvcController {

    private final AccountService accountService;

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable UUID id, Model model){
        model.addAttribute("user", accountService.getAccountById(id));
        return "profile";
    }

    @GetMapping("/profile")
    public String profileYourself(Principal principal, Model model) {
        AccountResponse accountResponse = accountService.getAccountByUsername(principal.getName());
        model.addAttribute("user", accountResponse);
        if (accountResponse.getStatus().equals("ADMIN")){
            return "admin";
        } else {
            return "profile";
        }
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
