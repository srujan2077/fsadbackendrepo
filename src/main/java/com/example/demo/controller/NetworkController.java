package com.example.demo.controller;

import com.example.demo.model.ServiceNode;
import com.example.demo.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/network")
// FIX: Changed port from 3000 to 5173 to match your Omen's Vite dev server
@CrossOrigin(origins = "*") 
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

    // ADDED: Support for the "EDIT_DATA" feature in your Admin Governance view
    @PutMapping("/update/{id}")
    public ServiceNode updateNode(@PathVariable Long id, @RequestBody ServiceNode nodeDetails) {
        return networkService.updateNodeDetails(id, nodeDetails);
    }

    @DeleteMapping("/revoke/{id}")
    public ResponseEntity<?> revokeNode(@PathVariable Long id) {
        networkService.revokeAccess(id);
        return ResponseEntity.ok().build();
    }
    
    // ADDED: Support for the Rating stars
    @PostMapping("/nodes/{id}/rate")
    public ServiceNode rateNode(@PathVariable Long id, @RequestBody java.util.Map<String, Integer> payload) {
        return networkService.processRating(id, payload.get("score"));
    }
}