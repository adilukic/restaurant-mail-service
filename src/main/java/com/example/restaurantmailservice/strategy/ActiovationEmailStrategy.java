package com.example.restaurantmailservice.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class ActiovationEmailStrategy implements EmailNotificationStrategy{
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendEmail(String recipient, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(generateBody());
        mailSender.send(message);
    }

    @Override
    public String generateBody() {
        return "Hvala Vam na registraciji,\nmolim Vas da kliknete na sledeci link radi potvrde:\n";
    }
}
