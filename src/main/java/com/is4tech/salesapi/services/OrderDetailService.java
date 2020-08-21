package com.is4tech.salesapi.services;

import com.is4tech.salesapi.dao.OrderDetailRepository;
import com.is4tech.salesapi.domain.OrderDetail;
import io.micrometer.core.annotation.Timed;
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
