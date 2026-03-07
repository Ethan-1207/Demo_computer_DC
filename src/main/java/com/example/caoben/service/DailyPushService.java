package com.example.caoben.service;

import com.example.caoben.model.DailyPushResponse;
import com.example.caoben.model.User;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class DailyPushService {

    private final UserService userService;

    public DailyPushService(UserService userService) {
        this.userService = userService;
    }

    public DailyPushResponse getTodayPush(String token) {
        DailyPushResponse response = new DailyPushResponse();

        User user = null;
        if (token != null && !token.isEmpty()) {
            user = userService.getUserByToken(token.replace("Bearer ", ""));
        }

        // 模拟根据日期动态获取节气（示例）
        String solarContent = getSolarTermContentByDate(LocalDate.now());

        // 组装节气数据
        DailyPushResponse.SolarTerm solar = new DailyPushResponse.SolarTerm();
        solar.setId("1");
        solar.setContent(solarContent);

        // AI课程 - 根据用户体质提供个性化建议
        DailyPushResponse.AiCourse ai = new DailyPushResponse.AiCourse();
        ai.setId("2");
        if (user != null && user.getConstitutionType() != null) {
            ai.setContent(getPersonalizedAIContent(user));
        } else {
            ai.setContent("今日主题：春季养肝护肝。建议您先完成AI体质辨识，获取更个性化的健康建议。");
        }
        ai.setAudioUrl("");

        // 防诈骗提醒
        DailyPushResponse.AntiFraud fraud = new DailyPushResponse.AntiFraud();
        fraud.setId("3");
        fraud.setContent("警惕包治百病类保健品骗局。");

        response.setSolarTerm(solar);
        response.setAiCourse(ai);
        response.setAntiFraud(fraud);

        return response;
    }

    public DailyPushResponse getTodayPush() {
        return getTodayPush(null);
    }

    // 根据用户体质生成个性化AI内容
    private String getPersonalizedAIContent(User user) {
        StringBuilder content = new StringBuilder();
        content.append("今日健康提醒：");
        
        if (user.getConstitutionType() != null) {
            content.append("\n您的体质类型：").append(user.getConstitutionType());
            content.append("\n");
            
            if (user.getHealthAdvice() != null && !user.getHealthAdvice().isEmpty()) {
                content.append("\n💡 今日建议：").append(user.getHealthAdvice());
            }
            
            if (user.getDietaryRecommendations() != null && !user.getDietaryRecommendations().isEmpty()) {
                content.append("\n🍲 饮食建议：").append(user.getDietaryRecommendations());
            }
            
            if (user.getExerciseRecommendations() != null && !user.getExerciseRecommendations().isEmpty()) {
                content.append("\n🏃 运动建议：").append(user.getExerciseRecommendations());
            }
        }
        
        content.append("\n\n💖 温馨提示：健康是一个持续的过程，保持良好的生活习惯，您一定能够拥有更健康的身体！");
        
        return content.toString();
    }

    // 模拟根据日期获取节气内容的方法（后期可对接真实节气API）
    private String getSolarTermContentByDate(LocalDate date) {
        // 这里可以写逻辑：根据日期判断当前节气，返回对应内容
        return "今日正值惊蛰时节，宜早睡早起，调养肝气。";
    }
}