package com.example.caoben.service;

import com.example.caoben.model.KnowledgeCategory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class KnowledgeService {

    public List<KnowledgeCategory> getCategories() {
        return Arrays.asList(
                new KnowledgeCategory("constitution", "体质养生", "了解9种中医体质特征..."),
                new KnowledgeCategory("chronic", "慢病调理", "高血压、糖尿病等调理方案..."),
                new KnowledgeCategory("solar", "节气养生", "二十四节气养生知识..."),
                new KnowledgeCategory("anti-fraud", "防骗科普", "识别养生谣言...")
        );
    }
}