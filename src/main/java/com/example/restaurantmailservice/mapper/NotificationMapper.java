package com.example.restaurantmailservice.mapper;

import com.example.restaurantmailservice.Dto.NotificationDto;
import com.example.restaurantmailservice.domain.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public Notification notificationDtoToNotification(NotificationDto notificationDto) {


        Notification notification = new Notification();
        notification.setRecipient(notificationDto.getRecipientEmail());
        notification.setSubject(notificationDto.getSubject());
        notification.setBody(notificationDto.getBody());
        notification.setNotificationType(notificationDto.getNotificationType());
        notification.setTimestamp(notificationDto.getTimestamp());
        notification.setArchived(false);

        return notification;
    }
    public NotificationDto notificationToNotificationDto(Notification notification){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setRecipientEmail(notification.getRecipient());
        notificationDto.setSubject(notification.getSubject());
        notificationDto.setBody(notification.getBody());
        notificationDto.setNotificationType(notification.getNotificationType());
        notificationDto.setTimestamp(notification.getTimestamp());
        return notificationDto;
    }
}
