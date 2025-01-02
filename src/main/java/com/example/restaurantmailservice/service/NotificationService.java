package com.example.restaurantmailservice.service;

import com.example.restaurantmailservice.Dto.NotificationDto;
import com.example.restaurantmailservice.domain.Notification;
import com.example.restaurantmailservice.mapper.NotificationMapper;
import com.example.restaurantmailservice.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationMapper notificationMapper;

    public NotificationDto saveNotification(NotificationDto notificationDto){
        Notification notification = notificationMapper.notificationDtoToNotification(notificationDto);
        notification.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notification);
        return notificationMapper.notificationToNotificationDto(notification);
    }
    public List<Notification> getArchivedNotifications() {
        return notificationRepository.findByArchived(true);
    }
}
