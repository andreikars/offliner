package com.offliner.offliner.repository;

import com.offliner.offliner.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepos extends JpaRepository<Order, Long> {
}
