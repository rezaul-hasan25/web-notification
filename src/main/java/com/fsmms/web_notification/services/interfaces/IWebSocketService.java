package com.fsmms.web_notification.services.interfaces;

import com.fsmms.web_notification.domain.Message;

public interface IWebSocketService {
    public void send(String clientId, Message message) throws Exception;
    public void sendPublic(Message message) throws Exception;
}
