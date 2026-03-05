package com.example.caoben.controller;

import com.example.caoben.model.KnowledgeCategory;
import com.example.caoben.service.KnowledgeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/knowledge")
@CrossOrigin
public class KnowledgeController {

    private final KnowledgeService knowledgeService;

    public KnowledgeController(KnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }

    @GetMapping("/categories")
    public List<KnowledgeCategory> getCategories() {
        return knowledgeService.getCategories();
    }
}