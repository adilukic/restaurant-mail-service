package com.example.restaurantmailservice.runner;

import com.example.restaurantmailservice.domain.Notification;
import com.example.restaurantmailservice.domain.NotificationType;
import com.example.restaurantmailservice.repo.NotificationRepository;
import com.example.restaurantmailservice.repo.NotificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private final NotificationTypeRepository notificationTypeRepository;
    private final NotificationRepository notificationRepository;

    @Autowired
    public TestDataRunner(NotificationTypeRepository notificationTypeRepository,
                          NotificationRepository notificationRepository) {
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Kreiranje i čuvanje NotificationType podataka
        NotificationType activationEmailType = new NotificationType("ACTIVATION_EMAIL", "Aktivacija naloga",
                "Pozdrav %ime%, aktivirajte vaš nalog klikom na sledeći link: %link%"
                );
        NotificationType passwordResetType = new NotificationType("PASSWORD_RESET","Resetovanje šifre",
                "Pozdrav %ime%, kliknite na sledeći link kako biste resetovali šifru: %link%"
                );
        NotificationType reservationCreatedType = new NotificationType("RESERVATION_CREATED", "Uspešna rezervacija",
                "Pozdrav %ime%, vaša rezervacija za %datum% u %vreme% je uspešno kreirana."
                );
        NotificationType reservationCancelledType = new NotificationType("RESERVATION_CANCELLED","Otkazivanje rezervacije",
                "Obaveštenje: Vaša rezervacija za %datum% u %vreme% je otkazana."
                );
        NotificationType reminderType = new NotificationType("REMINDER","Podsetnik za rezervaciju",
                "Podsetnik: Vaša rezervacija za %datum u %vreme% se bliži."
                );

        notificationTypeRepository.saveAll(Arrays.asList(
                activationEmailType,
                passwordResetType,
                reservationCreatedType,
                reservationCancelledType,
                reminderType
        ));

        // Kreiranje i čuvanje Notification podataka
        Notification notification1 = new Notification("user1@example.com",
                "Aktivacija naloga",
                "Pozdrav Marko, aktivirajte vaš nalog klikom na sledeći link: https://example.com/activate/123",
                "PROCESS", activationEmailType, LocalDateTime.now(), false);
        Notification notification2 = new Notification("user2@example.com",
                "Resetovanje šifre",
                "Pozdrav Jelena, kliknite na sledeći link kako biste resetovali šifru: https://example.com/reset/456",
                "PROCESS", passwordResetType, LocalDateTime.now(), false);
        Notification notification3 = new Notification("user3@example.com",
                "Uspešna rezervacija",
                "Pozdrav Ana, vaša rezervacija za 2025-01-10 u 19:30 je uspešno kreirana.",
                "PROCESS", reservationCreatedType, LocalDateTime.now(), false);

        notificationRepository.saveAll(Arrays.asList(notification1, notification2, notification3));

        // Log za potvrdu inicijalizacije
        System.out.println("NotificationType data: " + notificationTypeRepository.findAll());
        System.out.println("Notification data: " + notificationRepository.findAll());
    }
}
