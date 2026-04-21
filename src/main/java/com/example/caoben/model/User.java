package com.example.caoben.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    
    @Column(nullable = false, length = 100)
    private String password;
    
    @Column(length = 50)
    private String name;
    
    @Column(length = 10)
    private String gender;
    
    private Integer age;
    private Double height;
    private Double weight;
    
    @Column(name = "constitution_type", length = 50)
    private String constitutionType;
    
    @Column(name = "constitution_description", columnDefinition = "TEXT")
    private String constitutionDescription;
    
    @Column(name = "health_advice", columnDefinition = "TEXT")
    private String healthAdvice;
    
    @Column(name = "dietary_recommendations", columnDefinition = "TEXT")
    private String dietaryRecommendations;
    
    @Column(name = "exercise_recommendations", columnDefinition = "TEXT")
    private String exerciseRecommendations;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getConstitutionType() {
        return constitutionType;
    }

    public void setConstitutionType(String constitutionType) {
        this.constitutionType = constitutionType;
    }

    public String getConstitutionDescription() {
        return constitutionDescription;
    }

    public void setConstitutionDescription(String constitutionDescription) {
        this.constitutionDescription = constitutionDescription;
    }

    public String getHealthAdvice() {
        return healthAdvice;
    }

    public void setHealthAdvice(String healthAdvice) {
        this.healthAdvice = healthAdvice;
    }

    public String getDietaryRecommendations() {
        return dietaryRecommendations;
    }

    public void setDietaryRecommendations(String dietaryRecommendations) {
        this.dietaryRecommendations = dietaryRecommendations;
    }

    public String getExerciseRecommendations() {
        return exerciseRecommendations;
    }

    public void setExerciseRecommendations(String exerciseRecommendations) {
        this.exerciseRecommendations = exerciseRecommendations;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
