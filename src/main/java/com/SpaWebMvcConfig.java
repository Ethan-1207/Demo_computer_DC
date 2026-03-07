package com;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 适配SPA单页应用history路由的MVC配置
 */
@Configuration
public class SpaWebMvcConfig implements WebMvcConfigurer {

    // 1. 配置静态资源映射：确保前端打包的js、css、图片等能被正常访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 匹配所有静态资源请求，映射到你放前端打包文件的目录（默认是static）
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    // 2. 核心兜底配置：所有匹配不到后端接口的请求，统一返回index.html
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 通配符匹配所有路径，转发到index.html，让前端路由接管
        registry.addViewController("/**").setViewName("forward:/index.html");
    }
}