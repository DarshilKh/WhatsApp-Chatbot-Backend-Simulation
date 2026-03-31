package com.example.chatbot;

import com.example.chatbot.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ChatbotApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ChatService chatService;

    @Test
    void contextLoads() {
        assertThat(chatService).isNotNull();
    }

    @Test
    void hiReturnsHello() throws Exception {
        mockMvc.perform(post("/webhook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"message\": \"Hi\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }

    @Test
    void byeReturnsGoodbye() throws Exception {
        mockMvc.perform(post("/webhook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"message\": \"Bye\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Goodbye"));
    }

    @Test
    void unknownReturnsDefault() throws Exception {
        mockMvc.perform(post("/webhook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"message\": \"How are you?\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("I didn't understand that"));
    }

    @Test
    void caseInsensitiveAndTrimmed() throws Exception {
        mockMvc.perform(post("/webhook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"message\": \"  HI  \"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }

    @Test
    void emptyMessageReturnsDefault() throws Exception {
        mockMvc.perform(post("/webhook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"message\": \"\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("I didn't understand that"));
    }
}
