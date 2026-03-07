package com.example.caoben.model;

import java.util.List;

public class ConstitutionRequest {
    private String name;
    private String gender;
    private int age;
    private double height;
    private double weight;
    private boolean hasPeriod;
    private List<String> symptoms;
    private String customSymptoms;
    
    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
    
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    
    public boolean isHasPeriod() { return hasPeriod; }
    public void setHasPeriod(boolean hasPeriod) { this.hasPeriod = hasPeriod; }
    
    public List<String> getSymptoms() { return symptoms; }
    public void setSymptoms(List<String> symptoms) { this.symptoms = symptoms; }
    
    public String getCustomSymptoms() { return customSymptoms; }
    public void setCustomSymptoms(String customSymptoms) { this.customSymptoms = customSymptoms; }
}