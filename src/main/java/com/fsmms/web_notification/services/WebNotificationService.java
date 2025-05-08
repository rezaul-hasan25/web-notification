package com.fsmms.web_notification.services;

import com.fsmms.web_notification.core.BaseService;
import com.fsmms.web_notification.entity.Status;
import com.fsmms.web_notification.entity.WebNotification;
import com.fsmms.web_notification.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebNotificationService extends BaseService<WebNotification, NotificationRepository> {

    @Autowired
    private NotificationRepository notificationRepository;
    public List<WebNotification> search(String username, Status status){
        return notificationRepository.findByUsernameAndStatus(username, status);
    }
}
