package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "service_nodes")
public class ServiceNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String provider;
    private String rate;
    
    @Column(length = 1000)
    private String description;

    // --- MANUAL GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
    
    public String getRate() { return rate; }
    public void setRate(String rate) { this.rate = rate; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}