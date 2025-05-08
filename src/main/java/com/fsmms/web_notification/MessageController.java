package com.fsmms.web_notification;

import com.fsmms.web_notification.domain.Message;
import com.fsmms.web_notification.services.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MessageController {
    @Autowired
    private WebSocketService webSocketService;

    @PostMapping("/send-private/{userid}")
    public ResponseEntity<Void> sendMessage(@RequestBody final Message message, @PathVariable final String userid) throws Exception {
        webSocketService.send(userid,message);
        return ResponseEntity.ok().build();
    }

}
