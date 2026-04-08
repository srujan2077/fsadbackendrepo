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
}