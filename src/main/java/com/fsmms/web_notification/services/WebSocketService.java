package com.fsmms.web_notification.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsmms.web_notification.config.WebSocketProperties;
import com.fsmms.web_notification.domain.Message;
import com.fsmms.web_notification.entity.Status;
import com.fsmms.web_notification.entity.WebNotification;
import com.fsmms.web_notification.events.ActiveUserTracker;
import com.fsmms.web_notification.services.interfaces.IWebSocketService;
import com.fsmms.web_notification.utils.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class WebSocketService implements IWebSocketService {
    Logger logger = Logger.getLogger(WebSocketService.class.getName());
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    private WebNotificationService webNotificationService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebSocketProperties properties;


    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    @Override
    public void send(String clientId, Message message) throws Exception {
        logger.log(Level.INFO, "Message Sending ... : from {0} to {1} and message is {2}", new Object[]
                {message.getSender(), clientId, objectMapper.writeValueAsString(message)});
        if (DatabaseService.getInstance().isUserActive(clientId))
        {
            messagingTemplate.convertAndSendToUser(
                    clientId,
                    "/queue/notifications",
                    objectMapper.writeValueAsString(message)
            );
            logger.log(Level.INFO, "message sent.");
        }

        else{
            WebNotification webNotification = MessageConverter.toEntity(message,clientId);
            webNotification.setStatus(Status.PENDING);
            webNotificationService.save(webNotification);
            logger.log(Level.INFO, "client offline, sent failed.");
        }
    }

    @Override
    public void sendPublic(Message message) throws Exception {
        logger.log(Level.INFO, "sending public message {0}", objectMapper.writeValueAsString(message));
        messagingTemplate.convertAndSend(properties.getBrokerPrefixes().get(0).concat("/public"), objectMapper.writeValueAsString(message));
    }
}
