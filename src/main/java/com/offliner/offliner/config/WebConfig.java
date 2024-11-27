package com.offliner.offliner.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Разрешаем все пути
                .allowedOrigins("http://localhost:3000", "https://bumpy-ties-grow.loca.lt")  // Указываем фронтенд-адреса
                .allowedMethods("GET", "POST", "DELETE", "PUT")  // Разрешаем нужные HTTP-методы
                .allowedHeaders("*");  // Разрешаем все заголовки
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/product/**") // Обрабатываем запросы, начинающиеся с /product
                .addResourceLocations("file:public/"); // Указываем местоположение файлов
    }
}
