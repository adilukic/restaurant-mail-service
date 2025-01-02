package com.example.restaurantmailservice.controller;

import com.example.restaurantmailservice.Dto.NotificationTypeDto;
import com.example.restaurantmailservice.domain.NotificationType;
import com.example.restaurantmailservice.service.NotificationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications-types")
public class NotificationTypeController {
    @Autowired
    private NotificationTypeService notificationTypeService;

    @PostMapping("/add")
    public ResponseEntity<NotificationTypeDto> addNotificationType(@RequestBody NotificationTypeDto notificationTypeDto){
        NotificationTypeDto savedDto = notificationTypeService.saveNotificationType(notificationTypeDto);
        return ResponseEntity.ok(savedDto);
    }
}
