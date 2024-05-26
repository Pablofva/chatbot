package com.ajsw.chatbot_ono.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user_table")
public class User {

    @Id()
    @GeneratedValue( strategy =GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String gender;



}
