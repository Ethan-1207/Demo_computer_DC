package com.example.caoben.controller;

import com.example.caoben.model.User;
import com.example.caoben.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String username = request.get("username");
            String password = request.get("password");
            String name = request.get("name");

            if (username == null || password == null || name == null) {
                response.put("success", false);
                response.put("message", "用户名、密码和姓名不能为空");
                return response;
            }

            User user = userService.register(username, password, name);
            response.put("success", true);
            response.put("message", "注册成功");
            response.put("user", user);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String username = request.get("username");
            String password = request.get("password");

            if (username == null || password == null) {
                response.put("success", false);
                response.put("message", "用户名和密码不能为空");
                return response;
            }

            String token = userService.login(username, password);
            User user = userService.getUserByToken(token);

            response.put("success", true);
            response.put("message", "登录成功");
            response.put("token", token);
            response.put("user", user);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    @GetMapping("/info")
    public Map<String, Object> getUserInfo(@RequestHeader("Authorization") String authHeader) {
        Map<String, Object> response = new HashMap<>();
        try {
            String token = authHeader.replace("Bearer ", "");
            User user = userService.getUserByToken(token);

            if (user == null) {
                response.put("success", false);
                response.put("message", "用户未登录");
                return response;
            }

            response.put("success", true);
            response.put("user", user);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    @PostMapping("/update")
    public Map<String, Object> updateUser(@RequestHeader("Authorization") String authHeader, @RequestBody User userData) {
        Map<String, Object> response = new HashMap<>();
        try {
            String token = authHeader.replace("Bearer ", "");
            userService.updateUser(token, userData);

            User user = userService.getUserByToken(token);
            response.put("success", true);
            response.put("message", "更新成功");
            response.put("user", user);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(@RequestHeader("Authorization") String authHeader) {
        Map<String, Object> response = new HashMap<>();
        try {
            String token = authHeader.replace("Bearer ", "");
            userService.logout(token);
            response.put("success", true);
            response.put("message", "登出成功");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
}
