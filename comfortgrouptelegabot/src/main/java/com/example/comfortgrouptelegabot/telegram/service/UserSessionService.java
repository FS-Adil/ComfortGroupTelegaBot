package com.example.comfortgrouptelegabot.telegram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class UserSessionService {
    private final ConcurrentHashMap<Long, Boolean> userSessions = new ConcurrentHashMap<>();

    public boolean isFirstVisit(Long userId) {
        return !userSessions.containsKey(userId);
    }

    public void markAsVisited(Long userId) {
        userSessions.put(userId, true);
    }

    public int getTotalUsers() {
        return userSessions.size();
    }
}
