package com.example.notificationservice.config;

import com.example.notificationservice.handler.NotificationWebsocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class AppConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new NotificationWebsocketHandler(), "/ws/notification").setAllowedOrigins("*");
    }

    @Bean
    public NotificationWebsocketHandler notificationWebsocketHandler(){
        return new NotificationWebsocketHandler();
    }
}
