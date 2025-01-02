package com.example.restaurantmailservice.service;

import com.example.restaurantmailservice.Dto.NotificationTypeDto;
import com.example.restaurantmailservice.domain.NotificationType;
import com.example.restaurantmailservice.mapper.NotificationTypeMappper;
import com.example.restaurantmailservice.repo.NotificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationTypeService {
    @Autowired
    private NotificationTypeRepository notificationTypeRepository;
    @Autowired
    private NotificationTypeMappper notificationTypeMappper;

    public NotificationTypeDto saveNotificationType(NotificationTypeDto notificationTypeDto){
        NotificationType notificationType = notificationTypeMappper.notificationTypeDtoToNotificationType(notificationTypeDto);
        notificationTypeRepository.save(notificationType);
        return notificationTypeMappper.notificationTypeToNotificationTypeDto(notificationType);

    }

}
