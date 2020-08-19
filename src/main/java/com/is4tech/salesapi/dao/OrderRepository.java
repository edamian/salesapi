package com.is4tech.salesapi.dao;

import com.is4tech.salesapi.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
