package com.ajsw.chatbot_backend.controllers;

import com.ajsw.chatbot_backend.models.User;
import com.ajsw.chatbot_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/add")
    public ResponseEntity<Long> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        if (newUser != null) {
            return ResponseEntity.ok(newUser.getId());
        } else {
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/deleteall")
    public void deleteAll() {
        userService.deleteAll();
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}