package com.offliner.offliner.repository;

import com.offliner.offliner.model.Product;
import com.offliner.offliner.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepos extends JpaRepository<Product, Long> {
    // Изменили метод для поиска продуктов по объекту Category
    List<Product> findByCategory(Category category);
}
