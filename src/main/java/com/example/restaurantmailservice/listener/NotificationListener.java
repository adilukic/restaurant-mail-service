package com.example.restaurantmailservice.listener;

import com.example.restaurantmailservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @Autowired
    private NotificationService notificationService;

    @JmsListener(destination = "notificationQueue")
    public void listenForNotification(Long notificationId) {
        notificationService.processNotification(notificationId);
    }
}
