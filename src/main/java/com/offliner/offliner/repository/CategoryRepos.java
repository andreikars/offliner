package com.offliner.offliner.repository;

import com.offliner.offliner.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepos extends JpaRepository<Category, Long> {
    Category findByName(String name); // Метод для поиска категории по имени
}