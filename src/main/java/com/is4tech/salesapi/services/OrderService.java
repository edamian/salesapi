package com.is4tech.salesapi.services;

import com.is4tech.salesapi.dao.OrderRepository;
import com.is4tech.salesapi.domain.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order getById(Integer id) { return orderRepository.getOne(id);}
}
