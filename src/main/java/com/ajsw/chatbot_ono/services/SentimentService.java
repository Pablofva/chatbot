package com.ajsw.chatbot_ono.services;


import com.ajsw.chatbot_ono.models.Sentiment;
import com.ajsw.chatbot_ono.models.User;
import com.ajsw.chatbot_ono.repository.SentimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SentimentService {
    @Autowired
    private SentimentRepository sentimientoRepository;
    @Autowired
    private NLPClient nlpClient;
    @Autowired
    private UserService usuarioService;

    public Sentiment analizarSentimiento(Long usuarioId, String texto) {
        User usuario = usuarioService.getUserById(usuarioId);
       // String sentimiento = nlpClient.analizarSentimientos(texto);
        Sentiment sentimientoEntity = new Sentiment();
        sentimientoEntity.setUsuario(usuario);
       // sentimientoEntity.setSentimiento(sentimiento);
        sentimientoEntity.setFecha(LocalDateTime.now());
        return sentimientoRepository.save(sentimientoEntity);
    }
}
