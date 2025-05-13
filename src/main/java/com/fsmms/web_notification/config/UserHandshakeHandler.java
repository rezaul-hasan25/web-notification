package com.fsmms.web_notification.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import java.security.Principal;
import java.util.Map;


public class UserHandshakeHandler extends DefaultHandshakeHandler {

    private final Logger logger = LoggerFactory.getLogger(UserHandshakeHandler.class);

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        String userId = null;
        String query = request.getURI().getQuery(); // e.g., user=01715997160
        if (query != null && query.startsWith("user=")) {
            userId = query.split("=")[1]; // get user id
        }

        if (userId == null) {
            userId = "anonymous"; // default if missing
        }
        String finalUserId = userId;

        logger.info("Authenticate user {0}", finalUserId);
        return () -> finalUserId;
    }
}
