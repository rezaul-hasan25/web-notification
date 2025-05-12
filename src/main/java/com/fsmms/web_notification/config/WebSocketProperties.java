package com.fsmms.web_notification.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "websocket")
@Data
public class WebSocketProperties {
    private List<String> allowedOrigins;
    private List<String> brokerPrefixes;
    private String appDestinationPrefix;
    private String userDestinationPrefix;
    private String endpoint;
}
