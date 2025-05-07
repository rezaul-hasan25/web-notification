package com.fsmms.web_notification.services;

import com.fsmms.web_notification.domain.Message;
import com.fsmms.web_notification.config.WebSocketConfig;
import com.fsmms.web_notification.services.interfaces.IWebSocketService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService implements IWebSocketService {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    @Override
    public void send(String clientId, Message message) throws Exception {
        messagingTemplate.convertAndSendToUser(
                clientId,
                "/queue/notifications",
                message.getMessage()
        );
    }
}
