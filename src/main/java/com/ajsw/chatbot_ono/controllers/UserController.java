package com.ajsw.chatbot_ono.controllers;


import com.ajsw.chatbot_ono.models.User;
import com.ajsw.chatbot_ono.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    //servicio
    @Autowired
    private UserService userService;
    //@RequestMapping("/")
    //public String prueba(){
     //   return "Prueba Exitosa!";
   // }
    @PostMapping("/add")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "Usuario dado de alta!";
    }
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable ("id") long id){
    return userService.getUserById(id);
    }
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
