package com.ajsw.chatbot_backend.services;

import com.ajsw.chatbot_backend.models.enums.HogwartsHouse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HatChatbot {

    private final SentimentAnalysisService sentimentService;
    private final AnthropicClient anthropicClient;
    @Value("${claude.api.key}")
    private String apiKey;

    @Value("${claude.api.url}")
    private String apiUrl;
@Getter
    private final Map<String, List<String>> questions = new HashMap<>() {{
        put("¿Qué cualidad valoras más?", List.of("Valentía", "Inteligencia", "Lealtad", "Ambición"));
        put("¿Cuál es tu mayor miedo?", List.of("La oscuridad", "El fracaso", "La soledad", "El rechazo"));
        put("Si pudieras tener un poder mágico, ¿cuál sería?", List.of("Volar", "Leer mentes", "Hacer invisibles a otros", "Transformar objetos"));
        put("¿Qué tipo de criatura mágica te gustaría tener como mascota?", List.of("Dragón", "Fénix", "Hipogrifo", "Thestral"));
        put("¿En qué situación te sentirías más cómodo?", List.of("Liderando un grupo", "Resolviendo un acertijo complejo", "Ayudando a un amigo en necesidad", "Tomando una decisión difícil"));
        put("¿Qué te atrae más de Hogwarts?", List.of("Su historia y tradiciones", "Sus clases y conocimientos", "Sus amistades y compañerismo", "Sus oportunidades y desafíos"));
        put("¿Qué harías si encontraras un objeto mágico desconocido?", List.of("Experimentar con él", "Investigarlo en la biblioteca", "Entregarlo a un profesor", "Guardarlo en secreto"));
        put("¿Cuál es tu asignatura favorita?", List.of("Defensa Contra las Artes Oscuras", "Transformaciones", "Encantamientos", "Pociones"));
        put("Si pudieras cambiar algo de ti mismo, ¿qué sería?", List.of("Ser más valiente", "Ser más inteligente", "Ser más amable", "Ser más decidido"));
        put("¿Qué es lo que más admiras en otras personas?", List.of("El coraje", "La sabiduría", "La bondad", "La astucia"));
    }};
    @Autowired
    public HatChatbot(SentimentAnalysisService sentimentService, AnthropicClient anthropicClient) {
        this.sentimentService = sentimentService;
        this.anthropicClient = anthropicClient;
    }

    public String processInput(String userInput) {
        try {
            float sentimentScore = sentimentService.analyzeSentiment(userInput);
            String sentiment = sentimentScore > 0 ? "positive" : (sentimentScore < 0 ? "negative" : "neutral");

            String prompt = String.format("User input: %s", userInput);
            String response = anthropicClient.complete(prompt);

            return String.format("Sentiment: %s\nResponse: %s", sentiment, response);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error analyzing sentiment: " + e.getMessage();
        }
    }

    public HogwartsHouse assignHouse(List<String> interests) {
        // Implementa tu lógica de análisis aquí
        // Aquí simplemente retornamos una casa aleatoria como ejemplo
        HogwartsHouse[] houses = HogwartsHouse.values();
        int randomIndex = (int) (Math.random() * houses.length);
        return houses[randomIndex];
    }
}
