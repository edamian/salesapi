package com.is4tech.salesapi.repositories;

import com.is4tech.salesapi.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
