package com.example.restaurantmailservice.service;

import com.example.restaurantmailservice.Dto.NotificationDto;
import com.example.restaurantmailservice.domain.Notification;
import com.example.restaurantmailservice.domain.NotificationType;
import com.example.restaurantmailservice.mapper.NotificationMapper;
import com.example.restaurantmailservice.repo.NotificationRepository;
import com.example.restaurantmailservice.repo.NotificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private NotificationTypeRepository notificationTypeRepository;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private JavaMailSender mailSender;

    public NotificationDto saveNotification(NotificationDto notificationDto) {
        Notification notification = notificationMapper.notificationDtoToNotification(notificationDto);
        notification.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notification);
        return notificationMapper.notificationToNotificationDto(notification);
    }

    public List<Notification> getArchivedNotifications() {
        return notificationRepository.findByArchived(true);
    }

    public void sendNotification(String typeName, String recipient, Map<String, String> placeholders) {
        NotificationType notificationType = notificationTypeRepository.findByName(typeName)
                .orElseThrow(() -> new IllegalArgumentException("Notification type not found: " + typeName));

        String subject = replacePlaceholders(notificationType.getSubject(), placeholders);
        String body = replacePlaceholders(notificationType.getBody(), placeholders);

        Notification notification = new Notification();
        notification.setNotificationType(notificationType);
        notification.setRecipient(recipient);
        notification.setSubject(subject);
        notification.setBody(body);
        notification.setStatus("PROCESS");
        notificationRepository.save(notification);

        jmsTemplate.convertAndSend("notificationQueue", notification.getId());
    }

    public void processNotification(NotificationDto notificationDTO) {
        Notification notification = notificationRepository.findById(notificationDTO.getNotificationType().getId())
                .orElseThrow(() -> new IllegalArgumentException("Notification not found: " + notificationDTO.getNotificationType().getId()));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(notificationDTO.getRecipientEmail());
        message.setSubject(notificationDTO.getSubject());
        message.setText(notificationDTO.getBody());

        mailSender.send(message);
        notification.setStatus("SENT");
        notificationRepository.save(notification);
    }

    private String replacePlaceholders(String template, Map<String, String> placeholders) {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            template = template.replace("%" + entry.getKey() + "%", entry.getValue());
        }
        return template;
    }
}
