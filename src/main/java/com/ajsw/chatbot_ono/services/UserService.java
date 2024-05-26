package com.ajsw.chatbot_ono.services;


import com.ajsw.chatbot_ono.models.User;
import com.ajsw.chatbot_ono.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService implements IUserService{
    //repository
    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }
}
