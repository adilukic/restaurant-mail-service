package com.example.restaurantmailservice.repo;

import com.example.restaurantmailservice.domain.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationTypeRepository  extends JpaRepository<NotificationType, Long> {
    Optional<NotificationType> findByName(String name);
}
