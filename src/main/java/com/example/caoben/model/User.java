package com.example.caoben.model;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private Integer age;
    private Double height;
    private Double weight;
    private String constitutionType;
    private String constitutionDescription;
    private String healthAdvice;
    private String dietaryRecommendations;
    private String exerciseRecommendations;
    private LocalDateTime createdAt;
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
