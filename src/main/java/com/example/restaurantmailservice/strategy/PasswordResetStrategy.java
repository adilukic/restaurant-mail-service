package com.example.restaurantmailservice.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class PasswordResetStrategy implements EmailNotificationStrategy{
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendEmail(String recipient, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setTo(recipient);
        message.setText(generateBody());
        mailSender.send(message);
    }

    @Override
    public String generateBody() {
        return "Da biste resetovali sifru, kliknite sledeci link:\n";
    }
}
