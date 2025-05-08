package com.fsmms.web_notification.repo;

import com.fsmms.web_notification.entity.Status;
import com.fsmms.web_notification.entity.WebNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<WebNotification, Long> {
    List<WebNotification> findByUsernameAndStatus(String user, Status status);
}
