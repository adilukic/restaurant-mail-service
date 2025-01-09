package com.example.restaurantmailservice.listener;

import com.example.restaurantmailservice.Dto.NotificationCreateDto;
import com.example.restaurantmailservice.Dto.NotificationDto;
import com.example.restaurantmailservice.domain.NotificationType;
import com.example.restaurantmailservice.repo.NotificationTypeRepository;
import com.example.restaurantmailservice.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationListener {

    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;
    @Autowired
    public NotificationListener(ObjectMapper objectMapper, NotificationService notificationService) {
        this.objectMapper = objectMapper;
        this.notificationService = notificationService;
    }

    @JmsListener(destination = "notificationQueue")
    public void listenForNotification(String jsonMessage) {

        try {
            // 1. Deserijalizujte JSON u NotificationInputDto
            System.out.println(jsonMessage);
            NotificationCreateDto inputDto = objectMapper.readValue(jsonMessage, NotificationCreateDto.class);
            System.out.println("Primljen Input DTO: " + inputDto);

            // 2. Mapirajte NotificationInputDto u NotificationDto
            NotificationDto notificationDto = mapToNotificationDto(inputDto);

            // 3. Prosledite NotificationDto za obradu
            notificationService.processNotification(notificationDto);

        } catch (Exception e) {
            System.err.println("Greška prilikom obrade poruke: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private NotificationDto mapToNotificationDto(NotificationCreateDto inputDto) {
        // 1. Pronađite NotificationType iz baze na osnovu imena

        NotificationType notificationType = notificationTypeRepository.findByName(inputDto.getNotificationType())
                .orElseThrow(() -> new IllegalArgumentException("Notification type not found: " + inputDto.getNotificationType()));

        // 2. Kreirajte NotificationDto
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setRecipientEmail(inputDto.getEmail());
        notificationDto.setSubject(notificationType.getSubject()); // Prilagodite naslov
        notificationDto.setBody(notificationType.getBody()); // Prilagodite telo
        notificationDto.setNotificationType(notificationType); // Postavite NotificationType iz baze
        notificationDto.setTimestamp(LocalDateTime.now()); // Postavite trenutni timestamp

        return notificationDto;
    }
}