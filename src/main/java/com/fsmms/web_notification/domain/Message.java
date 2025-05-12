package com.fsmms.web_notification.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String message;
    private boolean ack;
    private String type;
    private String recipientId;
    private String sender;
}
