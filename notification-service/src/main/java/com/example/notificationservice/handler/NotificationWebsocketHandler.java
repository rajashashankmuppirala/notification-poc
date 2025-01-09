package com.example.notificationservice.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class NotificationWebsocketHandler extends TextWebSocketHandler {
    private static Set<WebSocketSession> sessions = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        log.info("New WebSocket connection established: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessions.remove(session);
        log.info("WebSocket connection closed: " + session.getId());
    }

    public void sendNotification(String message) {
        for (WebSocketSession session : sessions) {
            try {
                log.info("sending to session -->" + session.getId());
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
               log.error(e.getMessage());
            }
        }
    }


}
