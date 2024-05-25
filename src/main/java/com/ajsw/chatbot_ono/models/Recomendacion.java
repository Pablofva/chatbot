package com.ajsw.chatbot_ono.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

    @Entity
    @Getter
    @Setter
    public class Recomendacion {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToOne
        private User usuario;
        private String contenido;
        private LocalDateTime fechaGeneracion;
        // Getters y setters
    }

