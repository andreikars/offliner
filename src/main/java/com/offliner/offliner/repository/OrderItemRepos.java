package com.offliner.offliner.repository;

import com.offliner.offliner.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepos extends JpaRepository<OrderItem, Long> {
}
