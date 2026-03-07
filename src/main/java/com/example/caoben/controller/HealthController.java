package com.example.caoben.controller;

import com.example.caoben.service.HealthService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
@CrossOrigin
public class HealthController {

    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping("/data")
    public Map<String, Object> getHealthData(@RequestHeader("Authorization") String authHeader) {
        Map<String, Object> response = new HashMap<>();
        try {
            String token = authHeader.replace("Bearer ", "");
            Map<String, Object> healthData = healthService.getHealthData(token);
            response.put("success", true);
            response.put("data", healthData);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    @GetMapping("/ai-advice")
    public Map<String, Object> getAIAdvice(@RequestHeader("Authorization") String authHeader) {
        Map<String, Object> response = new HashMap<>();
        try {
            String token = authHeader.replace("Bearer ", "");
            Map<String, Object> advice = healthService.getAIAdvice(token);
            response.put("success", true);
            response.put("data", advice);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
}
