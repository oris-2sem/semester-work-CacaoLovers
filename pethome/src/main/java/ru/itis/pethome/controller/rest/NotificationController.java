package ru.itis.pethome.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.pethome.service.NotificationService;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public boolean sendMessage(){
        return notificationService.sendMessage("d","d");
    }

    public boolean sendManyMessage(){
        return notificationService.sendMessageAll(null, null);
    }
}
