package ru.itis.pethome.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationMailService implements NotificationService {

    private final JavaMailSender javaMailSender;
    @Override
    public boolean sendMessage(String recipient, String message) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo("marsel.gabitov.74@gmail.com");
            simpleMailMessage.setSubject("Пропала собака");
            simpleMailMessage.setText("Пропала собачка");
            javaMailSender.send(simpleMailMessage);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
