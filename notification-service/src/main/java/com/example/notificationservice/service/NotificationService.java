package com.example.notificationservice.service;

import com.example.notificationservice.handler.NotificationWebsocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    private final NotificationWebsocketHandler webSocketHandler;

    public NotificationService(NotificationWebsocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    public void publishNotification(String message) {
        webSocketHandler.sendNotification(message);
        log.info("message notification published");
    }
}
