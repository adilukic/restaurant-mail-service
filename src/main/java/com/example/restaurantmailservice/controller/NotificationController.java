package com.example.restaurantmailservice.controller;

import com.example.restaurantmailservice.Dto.NotificationDto;
import com.example.restaurantmailservice.domain.Notification;
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
    @PostMapping("/send")
    public ResponseEntity<NotificationDto> sendNotifications(@RequestBody NotificationDto notificationDto){
        NotificationDto savedDto = notificationService.saveNotification(notificationDto);
        return ResponseEntity.ok(savedDto);
    }
    @GetMapping("/archived")
    public ResponseEntity<List<Notification>> getArchivedNotifications(){
        return ResponseEntity.ok(notificationService.getArchivedNotifications());
    }
}
