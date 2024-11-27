package com.offliner.offliner.repository;

import com.offliner.offliner.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepos extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId);

    // Метод для поиска товара по userId и productId
    Cart findByUserIdAndProductId(Long userId, Long productId);
}