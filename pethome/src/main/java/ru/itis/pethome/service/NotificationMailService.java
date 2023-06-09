package ru.itis.pethome.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationMailService implements NotificationService {

    private final MailSender javaMailSender;
    @Override
    public boolean sendMessage(String recipient, String message) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(recipient);
            simpleMailMessage.setSubject("Увеодомление от сервиса Pethome");
            simpleMailMessage.setText(message);
            javaMailSender.send(simpleMailMessage);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
