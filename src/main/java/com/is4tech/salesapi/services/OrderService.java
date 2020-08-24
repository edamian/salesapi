package com.is4tech.salesapi.services;

import com.is4tech.salesapi.dao.OrderRepository;
import com.is4tech.salesapi.domain.Order;
import com.is4tech.salesapi.http.health.Adapter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final Adapter adapter;

    public OrderService(OrderRepository orderRepository, Adapter adapter) {
        this.orderRepository = orderRepository;
        this.adapter = adapter;
    }

    public List<Order> findAll() {
        if (adapter.isOk())
            return orderRepository.findAll();
        return  new ArrayList<>();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

}
