package com.example.caoben.controller;

import com.example.caoben.model.ConstitutionRequest;
import com.example.caoben.model.ConstitutionResponse;
import com.example.caoben.service.AIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin
public class ConstitutionAIController {
    
    private final AIService aiService;
    
    public ConstitutionAIController(AIService aiService) {
        this.aiService = aiService;
    }
    
    @PostMapping("/constitution-analysis")
    public ConstitutionResponse analyzeConstitution(@RequestBody ConstitutionRequest request) {
        return aiService.analyzeConstitution(request);
    }
}