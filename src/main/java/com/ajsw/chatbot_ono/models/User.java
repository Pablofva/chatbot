package com.ajsw.chatbot_ono.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="user_table")
public class User {

    @Id
    @GeneratedValue( strategy =GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String username;
    private String gender;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chatbot> conversations;


}
