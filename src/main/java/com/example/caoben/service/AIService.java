package com.example.caoben.service;

import com.example.caoben.model.ConstitutionRequest;
import com.example.caoben.model.ConstitutionResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AIService {

    @Value("${siliconflow.api.key:sk-xthuivlterkuzbktwflduepgwhhpmxabyzrplmsiyoqhjdyj}")
    private String apiKey;

    @Value("${siliconflow.api.url:https://api.siliconflow.cn/v1/chat/completions}")
    private String apiUrl;

    @Value("${siliconflow.model:deepseek-ai/DeepSeek-R1-Distill-Qwen-7B}")
    private String model;

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public AIService() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public ConstitutionResponse analyzeConstitution(ConstitutionRequest request) {
        System.out.println("=== 开始体质分析 ===");
        System.out.println("接收到的请求: " + request);
        System.out.println("姓名: " + request.getName());
        System.out.println("性别: " + request.getGender());
        System.out.println("年龄: " + request.getAge());
        System.out.println("症状: " + request.getSymptoms());
        System.out.println("自定义症状: " + request.getCustomSymptoms());

        try {
            // 构建提示词
            String prompt = buildPrompt(request);
            System.out.println("构建的提示词:\n" + prompt);

            // 调用硅基流动API
            String aiResponse = callSiliconFlowAPI(prompt);
            System.out.println("AI原始响应:\n" + aiResponse);

            // 解析AI响应
            ConstitutionResponse response = parseAIResponse(aiResponse, request);
            System.out.println("解析后的响应: " + response);
            System.out.println("=== 体质分析完成 ===");
            return response;

        } catch (Exception e) {
            System.err.println("AI分析失败，使用模拟数据: " + e.getMessage());
            e.printStackTrace();
            // 如果API调用失败，返回模拟数据
            return getMockResponse(request);
        }
    }

    private String buildPrompt(ConstitutionRequest request) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("你是一位专业的中医体质辨识专家。请根据以下用户信息，分析其体质类型，并提供详细的养生建议。\n\n");
        prompt.append("【用户基本信息】\n");
        prompt.append("姓名：").append(request.getName()).append("\n");
        prompt.append("性别：").append(request.getGender()).append("\n");
        prompt.append("年龄：").append(request.getAge()).append("岁\n");
        prompt.append("身高：").append(request.getHeight()).append("cm\n");
        prompt.append("体重：").append(request.getWeight()).append("kg\n");

        if ("女".equals(request.getGender()) && request.isHasPeriod()) {
            prompt.append("生理期：正常\n");
        }

        prompt.append("\n【症状描述】\n");
        if (request.getSymptoms() != null && !request.getSymptoms().isEmpty()) {
            prompt.append("常见症状：").append(String.join("、", request.getSymptoms())).append("\n");
        }
        if (request.getCustomSymptoms() != null && !request.getCustomSymptoms().isEmpty()) {
            prompt.append("其他症状：").append(request.getCustomSymptoms()).append("\n");
        }

        prompt.append("\n请根据中医九种体质（平和质、气虚质、阳虚质、阴虚质、痰湿质、湿热质、血瘀质、气郁质、特禀质）理论，分析该用户的体质类型。\n\n");
        prompt.append("请严格按照以下JSON格式返回结果（不要添加任何其他文字说明）：\n");
        prompt.append("{\n");
        prompt.append("  \"constitutionType\": \"体质类型名称\",\n");
        prompt.append("  \"constitutionDescription\": \"该体质的详细描述，包括主要特征和表现\",\n");
        prompt.append("  \"healthAdvice\": [\"养生建议1\", \"养生建议2\", \"养生建议3\", \"养生建议4\"],\n");
        prompt.append("  \"dietaryRecommendations\": [\"饮食建议1\", \"饮食建议2\", \"饮食建议3\", \"饮食建议4\"],\n");
        prompt.append("  \"exerciseRecommendations\": [\"运动建议1\", \"运动建议2\", \"运动建议3\", \"运动建议4\"]\n");
        prompt.append("}");

        return prompt.toString();
    }

    private String callSiliconFlowAPI(String prompt) throws IOException {
        // 构建请求体
        String requestBody = String.format(
            "{\"model\": \"%s\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}], \"temperature\": 0.7, \"max_tokens\": 2000}",
            model,
            prompt.replace("\"", "\\\"").replace("\n", "\\n")
        );

        RequestBody body = RequestBody.create(
            requestBody,
            MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
            .url(apiUrl)
            .post(body)
            .addHeader("Authorization", "Bearer " + apiKey)
            .addHeader("Content-Type", "application/json")
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("API调用失败: " + response.code() + " - " + response.body().string());
            }

            String responseBody = response.body().string();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            // 提取AI回复内容
            if (jsonNode.has("choices") && jsonNode.get("choices").isArray() && jsonNode.get("choices").size() > 0) {
                JsonNode message = jsonNode.get("choices").get(0).get("message");
                if (message != null && message.has("content")) {
                    return message.get("content").asText();
                }
            }

            throw new IOException("无法解析API响应");
        }
    }

    private ConstitutionResponse parseAIResponse(String aiResponse, ConstitutionRequest request) {
        System.out.println("开始解析AI响应...");
        ConstitutionResponse response = new ConstitutionResponse();

        try {
            // 尝试从AI响应中提取JSON
            String jsonStr = extractJsonFromResponse(aiResponse);
            System.out.println("尝试解析JSON...");
            
            JsonNode jsonNode = objectMapper.readTree(jsonStr);
            System.out.println("JSON解析成功");

            // 解析体质类型
            if (jsonNode.has("constitutionType")) {
                response.setConstitutionType(jsonNode.get("constitutionType").asText());
                System.out.println("体质类型: " + response.getConstitutionType());
            } else {
                response.setConstitutionType("平和质");
                System.out.println("未找到体质类型，使用默认值: 平和质");
            }

            // 解析体质描述
            if (jsonNode.has("constitutionDescription")) {
                response.setConstitutionDescription(jsonNode.get("constitutionDescription").asText());
                System.out.println("体质描述长度: " + response.getConstitutionDescription().length());
            } else {
                response.setConstitutionDescription("阴阳气血调和，以体态适中、面色红润、精力充沛等为主要特征。");
            }

            // 解析养生建议
            if (jsonNode.has("healthAdvice") && jsonNode.get("healthAdvice").isArray()) {
                JsonNode healthAdviceNode = jsonNode.get("healthAdvice");
                List<String> healthAdvice = new java.util.ArrayList<>();
                for (int i = 0; i < healthAdviceNode.size() && i < 4; i++) {
                    healthAdvice.add(healthAdviceNode.get(i).asText());
                }
                response.setHealthAdvice(healthAdvice);
                System.out.println("养生建议数量: " + healthAdvice.size());
            } else {
                response.setHealthAdvice(Arrays.asList(
                    "保持良好的生活习惯",
                    "坚持适度运动",
                    "均衡饮食",
                    "保持乐观心态"
                ));
                System.out.println("未找到养生建议，使用默认值");
            }

            // 解析饮食建议
            if (jsonNode.has("dietaryRecommendations") && jsonNode.get("dietaryRecommendations").isArray()) {
                JsonNode dietaryNode = jsonNode.get("dietaryRecommendations");
                List<String> dietaryRecommendations = new java.util.ArrayList<>();
                for (int i = 0; i < dietaryNode.size() && i < 4; i++) {
                    dietaryRecommendations.add(dietaryNode.get(i).asText());
                }
                response.setDietaryRecommendations(dietaryRecommendations);
                System.out.println("饮食建议数量: " + dietaryRecommendations.size());
            } else {
                response.setDietaryRecommendations(Arrays.asList(
                    "多吃蔬菜水果",
                    "适量摄入蛋白质",
                    "减少油腻食物",
                    "规律饮食"
                ));
                System.out.println("未找到饮食建议，使用默认值");
            }

            // 解析运动建议
            if (jsonNode.has("exerciseRecommendations") && jsonNode.get("exerciseRecommendations").isArray()) {
                JsonNode exerciseNode = jsonNode.get("exerciseRecommendations");
                List<String> exerciseRecommendations = new java.util.ArrayList<>();
                for (int i = 0; i < exerciseNode.size() && i < 4; i++) {
                    exerciseRecommendations.add(exerciseNode.get(i).asText());
                }
                response.setExerciseRecommendations(exerciseRecommendations);
                System.out.println("运动建议数量: " + exerciseRecommendations.size());
            } else {
                response.setExerciseRecommendations(Arrays.asList(
                    "散步",
                    "太极拳",
                    "八段锦",
                    "瑜伽"
                ));
                System.out.println("未找到运动建议，使用默认值");
            }

            System.out.println("AI响应解析完成");

        } catch (Exception e) {
            System.err.println("解析AI响应失败: " + e.getMessage());
            e.printStackTrace();
            // 如果解析失败，返回模拟数据
            return getMockResponse(request);
        }

        return response;
    }

    private String extractJsonFromResponse(String aiResponse) {
        System.out.println("提取JSON前的原始响应:\n" + aiResponse);
        
        // 尝试提取JSON部分
        int startIndex = aiResponse.indexOf("{");
        int endIndex = aiResponse.lastIndexOf("}");

        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            String jsonStr = aiResponse.substring(startIndex, endIndex + 1);
            System.out.println("提取的JSON字符串:\n" + jsonStr);
            return jsonStr;
        }

        System.out.println("未找到JSON，返回原始响应");
        return aiResponse;
    }

    private ConstitutionResponse getMockResponse(ConstitutionRequest request) {
        ConstitutionResponse response = new ConstitutionResponse();
        response.setConstitutionType("平和质");
        response.setConstitutionDescription("阴阳气血调和，以体态适中、面色红润、精力充沛等为主要特征。");

        response.setHealthAdvice(Arrays.asList(
            "保持良好的生活习惯",
            "坚持适度运动",
            "均衡饮食",
            "保持乐观心态"
        ));

        response.setDietaryRecommendations(Arrays.asList(
            "多吃蔬菜水果",
            "适量摄入蛋白质",
            "减少油腻食物",
            "规律饮食"
        ));

        response.setExerciseRecommendations(Arrays.asList(
            "散步",
            "太极拳",
            "八段锦",
            "瑜伽"
        ));

        return response;
    }
}
