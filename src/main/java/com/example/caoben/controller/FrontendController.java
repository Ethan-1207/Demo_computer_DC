package com.example.caoben.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/constitution")
    public String constitution() {
        return "constitution";
    }

    @GetMapping("/family")
    public String family() {
        return "family";
    }

    @GetMapping("/health")
    public String health() {
        return "health";
    }

    @GetMapping("/news")
    public String news() {
        return "news";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/rumor-check")
    public String rumorCheck() {
        return "rumor-check";
    }

    @GetMapping("/vip")
    public String vip() {
        return "vip";
    }
}

