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
    private String category; // Added for the React filters
    private String ownerAccountId; // Added to identify which user owns the node
    
    @Column(name = "description", length = 1000)
    private String desc; // Renamed to 'desc' to match the React 'newEntry' object

    // Stats for the "RatingStars" component
    private Double rating = 0.0;
    private Integer reviewCount = 0;

    public ServiceNode() {}

    // --- UPDATED GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
    
    public String getRate() { return rate; }
    public void setRate(String rate) { this.rate = rate; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getOwnerAccountId() { return ownerAccountId; }
    public void setOwnerAccountId(String ownerAccountId) { this.ownerAccountId = ownerAccountId; }
    
    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Integer getReviewCount() { return reviewCount; }
    public void setReviewCount(Integer reviewCount) { this.reviewCount = reviewCount; }
}