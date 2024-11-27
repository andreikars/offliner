package com.offliner.offliner.controller;

import com.offliner.offliner.model.Category;
import com.offliner.offliner.repository.CategoryRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepos categoryRepository;

    @GetMapping("/api/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll(); // Возвращаем все категории
    }
}
