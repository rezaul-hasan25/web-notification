package com.fsmms.web_notification.services;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DatabaseService {

    private final Set<String> activeUsers = ConcurrentHashMap.newKeySet();
    private static DatabaseService instance;
    private DatabaseService() {}
    public static synchronized DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }
    public void addUser(String username) {
        activeUsers.add(username);
    }
    public void removeUser(String username) {
        activeUsers.remove(username);
    }
    public boolean isUserActive(String username) {
        return activeUsers.contains(username);
    }
    public Set<String> getAllActiveUsers() {
        return activeUsers;
    }
}
