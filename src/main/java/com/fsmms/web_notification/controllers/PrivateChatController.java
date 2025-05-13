package com.fsmms.web_notification.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsmms.web_notification.domain.Message;
import com.fsmms.web_notification.services.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired private ObjectMapper objectMapper;

    @MessageMapping("/private")
    public ResponseEntity<Void> handlePrivateMessage(@Payload Message message, Principal principal) throws Exception {

        if (principal.getName() != null){
            webSocketService.send(message.getRecipientId(), message);
            return ResponseEntity.ok().build();
        }else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
