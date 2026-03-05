package com.example.caoben.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    /**
     * 处理前端路由，将所有非API请求重定向到index.html
     * 这样前端路由就能正常工作了
     */
    @RequestMapping(value = {"/about", "/constitution", "/family", "/health", "/news", "/profile", "/rumor-check", "/vip", "/{path:[^\\.]*}"})
    public String forward() {
        return "forward:/index.html";
    }
}

