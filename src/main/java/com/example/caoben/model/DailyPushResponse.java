package com.example.caoben.model;

public class DailyPushResponse {

    private SolarTerm solarTerm;
    private AiCourse aiCourse;
    private AntiFraud antiFraud;

    // getter & setter
    public SolarTerm getSolarTerm() {
        return solarTerm;
    }

    public void setSolarTerm(SolarTerm solarTerm) {
        this.solarTerm = solarTerm;
    }

    public AiCourse getAiCourse() {
        return aiCourse;
    }

    public void setAiCourse(AiCourse aiCourse) {
        this.aiCourse = aiCourse;
    }

    public AntiFraud getAntiFraud() {
        return antiFraud;
    }

    public void setAntiFraud(AntiFraud antiFraud) {
        this.antiFraud = antiFraud;
    }

    public static class SolarTerm {
        private String id;
        private String content;

        // getter & setter
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class AiCourse {
        private String id;
        private String content;
        private String audioUrl;

        // getter & setter
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAudioUrl() {
            return audioUrl;
        }

        public void setAudioUrl(String audioUrl) {
            this.audioUrl = audioUrl;
        }
    }

    public static class AntiFraud {
        private String id;
        private String content;

        // getter & setter
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}