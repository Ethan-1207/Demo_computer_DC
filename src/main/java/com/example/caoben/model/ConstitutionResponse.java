package com.example.caoben.model;

import java.util.List;

public class ConstitutionResponse {
    private String constitutionType;
    private String constitutionDescription;
    private List<String> healthAdvice;
    private List<String> dietaryRecommendations;
    private List<String> exerciseRecommendations;
    
    // Getters and setters
    public String getConstitutionType() { return constitutionType; }
    public void setConstitutionType(String constitutionType) { this.constitutionType = constitutionType; }
    
    public String getConstitutionDescription() { return constitutionDescription; }
    public void setConstitutionDescription(String constitutionDescription) { this.constitutionDescription = constitutionDescription; }
    
    public List<String> getHealthAdvice() { return healthAdvice; }
    public void setHealthAdvice(List<String> healthAdvice) { this.healthAdvice = healthAdvice; }
    
    public List<String> getDietaryRecommendations() { return dietaryRecommendations; }
    public void setDietaryRecommendations(List<String> dietaryRecommendations) { this.dietaryRecommendations = dietaryRecommendations; }
    
    public List<String> getExerciseRecommendations() { return exerciseRecommendations; }
    public void setExerciseRecommendations(List<String> exerciseRecommendations) { this.exerciseRecommendations = exerciseRecommendations; }
}