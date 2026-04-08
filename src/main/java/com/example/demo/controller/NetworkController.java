package com.example.demo.controller;

import com.example.demo.model.ServiceNode;
import com.example.demo.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/network")
@CrossOrigin(origins = "http://localhost:3000") // Connects to your Vite frontend
public class NetworkController {

    @Autowired
    private NetworkService networkService;

    @GetMapping("/nodes")
    public List<ServiceNode> getDirectory() {
        return networkService.getAllActiveNodes();
    }

    @PostMapping("/deploy")
    public ServiceNode deployNode(@RequestBody ServiceNode node) {
        return networkService.deployNewNode(node);
    }

    @DeleteMapping("/revoke/{id}")
    public ResponseEntity<?> revokeNode(@PathVariable Long id) {
        networkService.revokeAccess(id);
        return ResponseEntity.ok().build();
    }
}