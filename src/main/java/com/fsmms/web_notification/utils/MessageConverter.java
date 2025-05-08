package com.fsmms.web_notification.utils;

import com.fsmms.web_notification.domain.Message;
import com.fsmms.web_notification.entity.Type;
import com.fsmms.web_notification.entity.WebNotification;

public class MessageConverter {

    public static WebNotification toEntity(Message message, String userid){
        return WebNotification.builder()
                .message(message.getMessage())
                .ack(message.isAck())
                .username(userid)
                .type(Type.valueOf(message.getType()))
                .build();
    }

    public static Message toModel(WebNotification webNotification){
        return Message.builder()
                .message(webNotification.getMessage())
                .ack(webNotification.isAck())
                .type(webNotification.getType().toString())

                .build();
    }
}
