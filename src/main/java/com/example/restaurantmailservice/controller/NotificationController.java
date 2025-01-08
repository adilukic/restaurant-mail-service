package com.example.restaurantmailservice.controller;

import com.example.restaurantmailservice.Dto.NotificationDto;
import com.example.restaurantmailservice.Dto.NotificationRequestDto;
import com.example.restaurantmailservice.Dto.NotificationTypeDto;
import com.example.restaurantmailservice.domain.Notification;
import com.example.restaurantmailservice.domain.NotificationType;
import com.example.restaurantmailservice.repo.NotificationRepository;
import com.example.restaurantmailservice.repo.NotificationTypeRepository;
import com.example.restaurantmailservice.security.CheckSecurity;
import com.example.restaurantmailservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @CheckSecurity(roles = "")
    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationRequestDto request) {
        notificationService.sendNotification(request.getType(), request.getRecipient(), request.getPlaceholders());
        return "Notification queued for delivery!";
    }

    @PostMapping("/types")
    public String addNotificationType(@RequestBody NotificationTypeDto typeDto) {
        NotificationType type = new NotificationType();
        type.setName(typeDto.getName());
        type.setSubject(typeDto.getSubject());
        type.setBody(typeDto.getBody());
        notificationTypeRepository.save(type);
        return "Notification type added!";
    }

    @GetMapping("/history")
    public List<Notification> getNotificationHistory() {
        return notificationRepository.findAll();
    }
}

