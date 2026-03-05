package com.example.caoben.model;

public class KnowledgeCategory {

    private String code;
    private String name;
    private String description;

    public KnowledgeCategory(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    // getter
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}