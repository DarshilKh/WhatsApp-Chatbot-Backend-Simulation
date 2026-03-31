package com.example.chatbot.controller;

import com.example.chatbot.model.MessageRequest;
import com.example.chatbot.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {

    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);

    private final ChatService chatService;

    public WebhookController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "/webhook", produces = MediaType.TEXT_PLAIN_VALUE)
    public String handleMessage(@RequestBody MessageRequest request) {
        log.info("Received message: {}", request.getMessage());
        return chatService.processMessage(request.getMessage());
    }
}
