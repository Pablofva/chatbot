package com.ajsw.chatbot_ono.controllers;


import com.ajsw.chatbot_ono.models.User;
import com.ajsw.chatbot_ono.services.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public class UserController {
    //servicio
    private UserService userService;
    @RequestMapping("/")
    public String prueba(){
        return "Prueba Exitosa!";
    }
    @PostMapping("/add")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "Usuario dado de alta!";
    }
    @RequestMapping("/user/{id}")
    public User getUserById(@PathVariable ("id") long id){
    return userService.getUserById(id);
    }

}
