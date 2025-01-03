package com.example.restaurantmailservice.Dto;

import com.example.restaurantmailservice.domain.NotificationType;

import java.util.Map;

public class NotificationRequestDto {
    private String type;
    private String recipient;
    private Map<String, String> placeholders;

    public NotificationRequestDto() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Map<String, String> getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(Map<String, String> placeholders) {
        this.placeholders = placeholders;
    }
}
