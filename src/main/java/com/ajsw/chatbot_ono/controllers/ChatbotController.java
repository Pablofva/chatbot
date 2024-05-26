package com.ajsw.chatbot_ono.controllers;

import com.ajsw.chatbot_ono.models.Chatbot;
import com.ajsw.chatbot_ono.services.ChatbotService;
import com.ajsw.chatbot_ono.services.NLPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/chatbot")
    public class ChatbotController {

        @Autowired
        private NLPClient nlpClient;

        @Autowired
        private ChatbotService chatbotService;

        @PostMapping("/analyze/{userId}")
        public String analyzeText(@PathVariable long userId, @RequestBody String text) {
            String sentiment = nlpClient.analyzeText(text);
            chatbotService.saveConversation(userId, text, sentiment);
            return sentiment;
        }

        @GetMapping("/conversations")
        public List<Chatbot> getAllConversations() {
            return chatbotService.getAllConversations();
        }
    }

}
