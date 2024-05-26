package com.ajsw.chatbot_ono.repository;

import com.ajsw.chatbot_ono.models.Chatbot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatbotRepository extends CrudRepository<Chatbot, Long> {
}