package com.ajsw.chatbot_ono.services;

import com.ajsw.chatbot_ono.models.Chatbot;
import com.ajsw.chatbot_ono.models.User;
import com.ajsw.chatbot_ono.repository.ChatbotRepository;
import com.ajsw.chatbot_ono.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatbotService {

    @Autowired
    private ChatbotRepository chatbotRepository;

    @Autowired
    private UserRepository userRepository;

    public Chatbot saveConversation(long userId, String userInput, String botResponse) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Chatbot chatbot = new Chatbot();
        chatbot.setUser(user);
        chatbot.setUserInput(userInput);
        chatbot.setBotResponse(botResponse);
        chatbot.setTimestamp(LocalDateTime.now());
        return chatbotRepository.save(chatbot);
    }

    public List<Chatbot> getAllConversations() {
        return (List<Chatbot>) chatbotRepository.findAll();
    }
}
