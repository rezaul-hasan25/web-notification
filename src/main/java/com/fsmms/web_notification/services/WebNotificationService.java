package com.fsmms.web_notification.services;

import com.fsmms.web_notification.core.BaseService;
import com.fsmms.web_notification.entity.Status;
import com.fsmms.web_notification.entity.WebNotification;
import com.fsmms.web_notification.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class WebNotificationService extends BaseService<WebNotification, NotificationRepository> {

    private final Logger logger = Logger.getLogger(WebNotificationService.class.getName());
    @Autowired
    private NotificationRepository notificationRepository;
    public List<WebNotification> search(String username, Status status){
        logger.log(Level.INFO, "searching message for user {0}", username);
        return notificationRepository.findByUsernameAndStatus(username, status);
    }
}
