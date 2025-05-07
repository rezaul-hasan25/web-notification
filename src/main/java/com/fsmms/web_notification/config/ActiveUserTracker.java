package com.fsmms.web_notification.config;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class ActiveUserTracker {
    private Set<String> activeUsers = ConcurrentHashMap.newKeySet();

    @EventListener
    public void onConnect(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        if (sha.getUser() != null) {
            activeUsers.add(sha.getUser().getName());

        }
    }


    @EventListener
    public void onDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        if (sha.getUser() != null) {
            activeUsers.remove(sha.getUser().getName());
            System.out.println("Active Users: " + activeUsers);
        }
    }

    public Set<String> getActiveUsers() {
        return activeUsers;
    }
}
