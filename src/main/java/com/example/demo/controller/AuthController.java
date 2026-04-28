package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/network")
@CrossOrigin(
    origins = "*", 
    allowedHeaders = "*", 
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    // LOGIN ENDPOINT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        return userRepo.findByUsername(loginRequest.getUsername())
            .filter(user -> user.getPassword().equals(loginRequest.getPassword()))
            .map(user -> ResponseEntity.ok(user))
            .orElse(ResponseEntity.status(401).build());
    }
    
    // REGISTRATION ENDPOINT
    @PostMapping("/register")
    public User register(@RequestBody User newUser) {
        // This persists the new 'Client' or 'Professional' node to your cloud DB
        return userRepo.save(newUser);
    }

    // HELPER: FETCH ALL USERS (Optional, for Governance view)
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
