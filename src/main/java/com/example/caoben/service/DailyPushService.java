package com.example.caoben.service;

import com.example.caoben.model.DailyPushResponse;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class DailyPushService {

    public DailyPushResponse getTodayPush() {
        DailyPushResponse response = new DailyPushResponse();

        // 模拟根据日期动态获取节气（示例）
        String solarContent = getSolarTermContentByDate(LocalDate.now());

        // 组装节气数据
        DailyPushResponse.SolarTerm solar = new DailyPushResponse.SolarTerm();
        solar.setId("1");
        solar.setContent(solarContent);

        // 其他数据组装逻辑不变...
        DailyPushResponse.AiCourse ai = new DailyPushResponse.AiCourse();
        ai.setId("2");
        ai.setContent("今日主题：春季养肝护肝。");
        ai.setAudioUrl("");

        DailyPushResponse.AntiFraud fraud = new DailyPushResponse.AntiFraud();
        fraud.setId("3");
        fraud.setContent("警惕包治百病类保健品骗局。");

        response.setSolarTerm(solar);
        response.setAiCourse(ai);
        response.setAntiFraud(fraud);

        return response;
    }

    // 模拟根据日期获取节气内容的方法（后期可对接真实节气API）
    private String getSolarTermContentByDate(LocalDate date) {
        // 这里可以写逻辑：根据日期判断当前节气，返回对应内容
        return "今日正值惊蛰时节，宜早睡早起，调养肝气。";
    }
}