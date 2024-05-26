package com.ajsw.chatbot_ono.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NLPClient {

    @Value("${nlpcloud.api.key}")
    private String apiKey;

    private final String apiUrl = "https://api.nlpcloud.io/v1/your_model/analyze";

    public String analyzeText(String text) {
        RestTemplate restTemplate = new RestTemplate();
        // Configurar la solicitud HTTP
        return "Sentimiento detectado";
    }

}