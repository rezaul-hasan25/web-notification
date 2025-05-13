package com.fsmms.web_notification.controllers;


import com.fsmms.web_notification.domain.Message;
import com.fsmms.web_notification.services.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/send")
public class MessageController {

    @Autowired private WebSocketService socketService;

    @PostMapping("/private")
    public ResponseEntity<Void> sendPrivate(@Validated @RequestBody Message message) throws Exception {
        this.socketService.send(message.getRecipientId(), message);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/")
    public ResponseEntity<Void> sendPublic(@Validated @RequestBody Message message) throws Exception {
        this.socketService.sendPublic(message);
        return ResponseEntity.ok().build();
    }


}
