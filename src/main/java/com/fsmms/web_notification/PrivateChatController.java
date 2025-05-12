package com.fsmms.web_notification;

import com.fsmms.web_notification.domain.Message;
import com.fsmms.web_notification.services.WebNotificationService;
import com.fsmms.web_notification.services.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class PrivateChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private WebSocketService webSocketService;

    @MessageMapping("/private")
    public void handlePrivateMessage(@Payload Message message, Principal principal) throws Exception {
        if (principal.getName() != null)
            webSocketService.send(message.getRecipientId(), message);

    }
}
