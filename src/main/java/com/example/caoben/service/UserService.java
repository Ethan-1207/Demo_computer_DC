package com.example.caoben.service;

import com.example.caoben.model.User;
import com.example.caoben.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    private final Map<String, String> sessions = new ConcurrentHashMap<>();

    public User register(String username, String password, String name) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("用户名已存在");
        }

        User user = new User(username, password);
        user.setName(name);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        
        return userRepository.save(user);
    }

    public String login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty() || !userOpt.get().getPassword().equals(password)) {
            throw new RuntimeException("用户名或密码错误");
        }

        String token = generateToken();
        sessions.put(token, username);
        return token;
    }

    public User getUserByToken(String token) {
        String username = sessions.get(token);
        if (username == null) {
            return null;
        }
        return userRepository.findByUsername(username).orElse(null);
    }

    public void updateUser(String token, User userData) {
        User user = getUserByToken(token);
        if (user == null) {
            throw new RuntimeException("用户未登录");
        }

        if (userData.getName() != null) user.setName(userData.getName());
        if (userData.getGender() != null) user.setGender(userData.getGender());
        if (userData.getAge() != null) user.setAge(userData.getAge());
        if (userData.getHeight() != null) user.setHeight(userData.getHeight());
        if (userData.getWeight() != null) user.setWeight(userData.getWeight());
        if (userData.getConstitutionType() != null) user.setConstitutionType(userData.getConstitutionType());
        if (userData.getConstitutionDescription() != null) user.setConstitutionDescription(userData.getConstitutionDescription());
        if (userData.getHealthAdvice() != null) user.setHealthAdvice(userData.getHealthAdvice());
        if (userData.getDietaryRecommendations() != null) user.setDietaryRecommendations(userData.getDietaryRecommendations());
        if (userData.getExerciseRecommendations() != null) user.setExerciseRecommendations(userData.getExerciseRecommendations());

        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    public void logout(String token) {
        sessions.remove(token);
    }

    private String generateToken() {
        return "token_" + System.currentTimeMillis() + "_" + (int)(Math.random() * 10000);
    }
}
