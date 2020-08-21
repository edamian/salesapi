package com.is4tech.salesapi.services;

import com.is4tech.salesapi.dao.OrderRepository;
import com.is4tech.salesapi.domain.Order;
import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Timed("GET_ORDERS")
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Timed("SAVE_ORDER")
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Timed("GET_ORDER_BY_ID")
    public Order getById(Integer id) { return orderRepository.getOne(id);}
}
