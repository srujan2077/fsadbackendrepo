package com.example.demo.repository;

import com.example.demo.model.ServiceNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServiceNodeRepository extends JpaRepository<ServiceNode, Long> {
    // Custom search for your ProConnect search bar
    List<ServiceNode> findByTitleContainingIgnoreCaseOrProviderContainingIgnoreCase(String title, String provider);
}