package com.example.chatbot.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatService {

    private static final Map<String, String> RESPONSES = Map.of(
            "hi", "Hello",
            "bye", "Goodbye"
    );

    private static final String DEFAULT_RESPONSE = "I didn't understand that";

    public String processMessage(String message) {
        if (message == null || message.isBlank()) {
            return DEFAULT_RESPONSE;
        }
        String normalized = message.trim().toLowerCase();
        return RESPONSES.getOrDefault(normalized, DEFAULT_RESPONSE);
    }
}
