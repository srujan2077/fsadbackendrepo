package com.example.demo.service;

import com.example.demo.model.ServiceNode;
import com.example.demo.repository.ServiceNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NetworkService {

    @Autowired
    private ServiceNodeRepository repository;

    // Logic to get all nodes from MySQL
    public List<ServiceNode> getAllActiveNodes() {
        return repository.findAll();
    }

    // Logic to save a new node (pushed from your React form)
    public ServiceNode deployNewNode(ServiceNode node) {
        return repository.save(node);
    }

    // Logic to delete a node by ID
    public void revokeAccess(Long id) {
        repository.deleteById(id);
    }

    // --- NEW: Logic for the "EDIT_DATA" button in Admin View ---
    public ServiceNode updateNodeDetails(Long id, ServiceNode details) {
        ServiceNode node = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Node not found with id: " + id));
        
        node.setTitle(details.getTitle());
        node.setRate(details.getRate());
        node.setDesc(details.getDesc()); // This matches the 'desc' field we updated in the Model
        node.setCategory(details.getCategory());
        
        return repository.save(node);
    }

    // --- NEW: Logic for the Rating Stars handshake ---
    public ServiceNode processRating(Long id, Integer newScore) {
        ServiceNode node = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Node not found with id: " + id));
        
        double currentRating = node.getRating();
        int currentCount = node.getReviewCount();
        
        // Mathematical average update for the Review system
        double totalScore = (currentRating * currentCount) + newScore;
        node.setReviewCount(currentCount + 1);
        node.setRating(totalScore / (currentCount + 1));
        
        return repository.save(node);
    }
}