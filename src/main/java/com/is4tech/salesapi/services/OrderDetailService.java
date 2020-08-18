package com.is4tech.salesapi.services;

import com.is4tech.salesapi.models.OrderDetail;
import com.is4tech.salesapi.repositories.OrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetail> saveAll(List<OrderDetail> details) {
        return orderDetailRepository.saveAll(details);
    }
}
