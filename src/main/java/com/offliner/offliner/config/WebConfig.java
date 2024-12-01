package com.offliner.offliner.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://8.211.51.110:3000", "http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600); // Установите максимальное время кэширования preflight запроса
        registry.addMapping("/product")  // Обрабатываем POST-запросы на /product
                .allowedOrigins("http://8.211.51.110:3000")
                .allowedMethods("POST", "OPTIONS", "GET", "PUT")  // Только POST и OPTIONS методы
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);  // Максимальное время кэширования preflight запроса
    }




    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/product/**") // Обрабатываем запросы, начинающиеся с /product
                .addResourceLocations("file:public/"); // Указываем местоположение файлов
    }
}

