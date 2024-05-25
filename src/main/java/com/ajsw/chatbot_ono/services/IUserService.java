package com.ajsw.chatbot_ono.services;

import com.ajsw.tp.models.User;

public interface IUserService {
    public User addUser(User user);
    public User getUserById(long id);


}
