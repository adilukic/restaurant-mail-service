package com.example.restaurantmailservice.mapper;

import com.example.restaurantmailservice.Dto.NotificationTypeDto;
import com.example.restaurantmailservice.domain.NotificationType;
import org.springframework.stereotype.Component;

@Component
public class NotificationTypeMappper {
    public NotificationType notificationTypeDtoToNotificationType(NotificationTypeDto notificationTypeDto) {

        NotificationType notificationType = new NotificationType();
        notificationType.setName(notificationTypeDto.getName());
        notificationType.setDescription(notificationTypeDto.getDescription());
        notificationType.setActive(notificationTypeDto.isActive());

        return notificationType;
    }

    public NotificationTypeDto notificationTypeToNotificationTypeDto(NotificationType notificationType) {


        NotificationTypeDto notificationTypeDto = new NotificationTypeDto();
        notificationTypeDto.setName(notificationType.getName());
        notificationTypeDto.setDescription(notificationType.getDescription());
        notificationTypeDto.setActive(notificationType.isActive());

        return notificationTypeDto;
    }
}
