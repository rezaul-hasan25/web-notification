package com.fsmms.web_notification.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsmms.web_notification.entity.Status;
import com.fsmms.web_notification.services.DatabaseService;
import com.fsmms.web_notification.services.WebNotificationService;
import com.fsmms.web_notification.services.WebSocketService;
import com.fsmms.web_notification.utils.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class ActiveUserTracker {

    private final Logger logger = Logger.getLogger(ActiveUserTracker.class.getName());
    @Autowired private WebNotificationService webNotificationService;
    @Autowired private WebSocketService webSocketService;
    @Autowired private ObjectMapper objectMapper;

    @EventListener
    public void onConnect(SessionConnectEvent event) throws JsonProcessingException {
        logger.log(Level.INFO, "onConnect called. data {0}", objectMapper.writeValueAsString(event));
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        if (sha.getUser() != null) {
            DatabaseService.getInstance().addUser(sha.getUser().getName());
        }
    }

    @EventListener
    public void handleWebSocketSubscribeListener(SessionSubscribeEvent event) throws JsonProcessingException {
        logger.log(Level.INFO, "handleWebSocketSubscribeListener called. data {0}", objectMapper.writeValueAsString(event));
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());

        webNotificationService.search(Objects.requireNonNull(sha.getUser()).getName(), Status.PENDING)
        .forEach(
                webNotification -> {
                    try {
                        webSocketService.send(sha.getUser().getName(), MessageConverter.toModel(webNotification));
                        webNotificationService.deleteById(webNotification.getId());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }


    @EventListener
    public void onDisconnect(SessionDisconnectEvent event) throws JsonProcessingException {
        logger.log(Level.INFO, "handleWebSocketSubscribeListener called. data {0}", objectMapper.writeValueAsString(event));
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        if (sha.getUser() != null) {
            DatabaseService.getInstance().removeUser(sha.getUser().getName());
            System.out.println("Disconnected Users: " + sha.getUser().getName());
        }
    }
}
