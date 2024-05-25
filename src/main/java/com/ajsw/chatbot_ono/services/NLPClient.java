package com.ajsw.chatbot_ono.services;

import com.ajsw.tp.controllers.HistorialNavegacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NLPClient {
    private final String API_URL = "https://api.nlpcloud.io/v1/your-endpoint";
    private final String API_KEY = "44bfd9b489d27cd9f9b7dcf8c1c4bd7433d3aaf5";

    public String analizarSentimientos(List<HistorialNavegacion> historial) {
        // Implementa la lógica para llamar a la API de NLP Cloud
    }
}