package com.ajsw.chatbot_backend.services;

import com.ajsw.chatbot_backend.models.User;
import com.ajsw.chatbot_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
