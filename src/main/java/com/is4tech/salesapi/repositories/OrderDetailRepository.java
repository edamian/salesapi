package com.is4tech.salesapi.repositories;

import com.is4tech.salesapi.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
