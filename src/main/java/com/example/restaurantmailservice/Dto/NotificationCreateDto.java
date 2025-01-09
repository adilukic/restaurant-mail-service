package com.example.restaurantmailservice.Dto;

import com.example.restaurantmailservice.domain.NotificationType;

public class NotificationCreateDto {
    private Long userId;
    private String email;
    private String notificationType;

    public NotificationCreateDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", notificationType=" + notificationType +
                '}';
    }
}

