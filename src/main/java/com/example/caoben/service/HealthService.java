package com.example.caoben.service;

import com.example.caoben.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HealthService {
    private final UserService userService;

    public HealthService(UserService userService) {
        this.userService = userService;
    }

    public Map<String, Object> getHealthData(String token) {
        User user = userService.getUserByToken(token);
        if (user == null) {
            throw new RuntimeException("用户未登录");
        }

        Map<String, Object> healthData = new HashMap<>();

        if (user.getConstitutionType() != null) {
            healthData.put("constitutionType", user.getConstitutionType());
            healthData.put("constitutionDescription", user.getConstitutionDescription());
            healthData.put("healthAdvice", user.getHealthAdvice());
            healthData.put("dietaryRecommendations", user.getDietaryRecommendations());
            healthData.put("exerciseRecommendations", user.getExerciseRecommendations());
        }

        if (user.getName() != null) {
            healthData.put("name", user.getName());
        }
        if (user.getGender() != null) {
            healthData.put("gender", user.getGender());
        }
        if (user.getAge() != null) {
            healthData.put("age", user.getAge());
        }
        if (user.getHeight() != null) {
            healthData.put("height", user.getHeight());
        }
        if (user.getWeight() != null) {
            healthData.put("weight", user.getWeight());
        }

        return healthData;
    }

    public Map<String, Object> getAIAdvice(String token) {
        User user = userService.getUserByToken(token);
        if (user == null) {
            throw new RuntimeException("用户未登录");
        }

        Map<String, Object> advice = new HashMap<>();

        if (user.getConstitutionType() != null) {
            advice.put("hasConstitution", true);
            advice.put("constitutionType", user.getConstitutionType());
            advice.put("healthAdvice", user.getHealthAdvice());
            advice.put("dietaryRecommendations", user.getDietaryRecommendations());
            advice.put("exerciseRecommendations", user.getExerciseRecommendations());
        } else {
            advice.put("hasConstitution", false);
            advice.put("message", "您还没有进行体质辨识，建议先完成AI体质辨识，获取个性化的健康建议");
            advice.put("encouragement", "健康从了解自己开始，让我们一起开启您的健康之旅吧！");
        }

        return advice;
    }
}
