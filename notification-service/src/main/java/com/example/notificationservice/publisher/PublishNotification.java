package com.example.notificationservice.publisher;

import com.example.notificationservice.service.NotificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class PublishNotification {

    private final NotificationService notificationService;
    Random random = new Random();

    public PublishNotification(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    public void sendPeriodicSuccessNotifications() {
        notificationService.publishNotification("This is an success alert notification!");
    }

    public void sendPeriodicFailureNotifications() {
        notificationService.publishNotification("This is an failure alert notification!");
    }

    public void sendPeriodicWarningNotifications() {
        notificationService.publishNotification("This is an warning alert notification!");
    }

    public void sendPeriodicInfoNotifications() {
        notificationService.publishNotification("This is an info alert notification!");
    }

    @Scheduled(fixedRate = 5000)
    private void publishRandomNotification(){
        int methodNumber = random.nextInt(4);
        switch (methodNumber) {
            case 0:
                sendPeriodicSuccessNotifications();
                break;
            case 1:
                sendPeriodicFailureNotifications();
                break;
            case 2:
                sendPeriodicWarningNotifications();
                break;
            case 3:
                sendPeriodicInfoNotifications();
                break;
        }

    }
}
