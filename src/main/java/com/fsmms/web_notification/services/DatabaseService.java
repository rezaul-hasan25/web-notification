package com.fsmms.web_notification.services;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DatabaseService {

    // Thread-safe set to store active usernames
    private final Set<String> activeUsers = ConcurrentHashMap.newKeySet();

    // Private static instance of the Singleton
    private static DatabaseService instance;

    // Private constructor to prevent instantiation
    private DatabaseService() {}

    // Public static method to provide access to the Singleton instance
    public static synchronized DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    // Method to add a username to the activeUsers set
    public void addUser(String username) {
        activeUsers.add(username);
    }

    // Method to remove a username from the activeUsers set
    public void removeUser(String username) {
        activeUsers.remove(username);
    }

    // Method to check if a username is active
    public boolean isUserActive(String username) {
        return activeUsers.contains(username);
    }

    // Method to retrieve all active usernames
    public Set<String> getAllActiveUsers() {
        return activeUsers;
    }
}
