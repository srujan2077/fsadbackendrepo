package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/network")
@CrossOrigin(origins = "http://localhost:5173") // Connects to your Omen's Vite port
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        // This looks into your proconnectdb 'users' table
        return userRepo.findByUsername(loginRequest.getUsername())
            .filter(user -> user.getPassword().equals(loginRequest.getPassword()))
            .map(user -> ResponseEntity.ok(user))
            .orElse(ResponseEntity.status(401).build());
    }
    
    @PostMapping("/register")
    public User register(@RequestBody User newUser) {
        return userRepo.save(newUser);
    }
}