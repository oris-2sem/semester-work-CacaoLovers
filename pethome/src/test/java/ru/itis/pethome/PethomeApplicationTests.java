package ru.itis.pethome;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.mail.MailHealthContributorAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import ru.itis.pethome.service.NotificationService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class PethomeApplicationTests {

    @Autowired
    private NotificationService notificationService;

    @MockBean
    private MailSender mailSender;

    @MockBean
    private MailHealthContributorAutoConfiguration mailHealthContributorAutoConfiguration;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSendEmail(){
        String recipient = "marsel.gabitov.74@gmail.com";
        String subject = "Test Email";

        notificationService.sendMessage(recipient, subject);

        verify(mailSender).send(any(SimpleMailMessage.class));
    }

}
