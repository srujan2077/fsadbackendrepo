package com.example.demo.controller;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.User;
import com.example.demo.repository.ChatMessageRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/network")
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {

    @Autowired
    private ChatMessageRepository chatRepo;

    @Autowired
    private UserRepository userRepo; // Connects to your users table

    // Fetch message history between two users
    @GetMapping("/messages/{user1}/{user2}")
    public List<ChatMessage> getHistory(@PathVariable String user1, @PathVariable String user2) {
        return chatRepo.findChatHistory(user1, user2);
    }

    // Save a new message to MySQL
    @PostMapping("/messages")
    public ChatMessage sendMessage(@RequestBody ChatMessage message) {
        return chatRepo.save(message);
    }

    // THE FIX: Pulls every user from MySQL to populate your sidebar!
    @GetMapping("/conversations/{username}")
    public ResponseEntity<?> getConversations(@PathVariable String username) {
        List<String> contacts = userRepo.findAll().stream()
            .map(User::getUsername)
            .filter(name -> !name.equals(username)) // Don't show yourself in the list
            .collect(Collectors.toList());
            
        return ResponseEntity.ok(contacts);
    }
}