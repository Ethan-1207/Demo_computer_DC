package com.example.caoben.controller;

import com.example.caoben.model.DailyPushResponse;
import com.example.caoben.service.DailyPushService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/daily-push")
@CrossOrigin
public class DailyController {

    private final DailyPushService dailyPushService;

    // 构造器注入（Spring Boot推荐）
    public DailyController(DailyPushService dailyPushService) {
        this.dailyPushService = dailyPushService;
    }

    @GetMapping
    public DailyPushResponse getTodayPush() {
        return dailyPushService.getTodayPush();
    }
}