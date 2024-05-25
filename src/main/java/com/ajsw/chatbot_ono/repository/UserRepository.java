package com.ajsw.chatbot_ono.repository;

import com.ajsw.tp.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
