package com.example.restaurantmailservice.strategy;

public interface EmailNotificationStrategy {
    void sendEmail(String recipient, String subject);
    String generateBody();
}
