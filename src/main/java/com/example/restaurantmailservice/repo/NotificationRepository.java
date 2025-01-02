package com.example.restaurantmailservice.repo;

import com.example.restaurantmailservice.domain.Notification;
import com.example.restaurantmailservice.domain.NotificationTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByNotificationType(NotificationTypeEnum notificationType);

    List<Notification> findByArchived(boolean archived);
}
