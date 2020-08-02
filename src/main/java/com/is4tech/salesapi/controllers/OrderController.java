package com.is4tech.salesapi.controllers;

import com.is4tech.salesapi.models.*;
import com.is4tech.salesapi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;
    private StatusRepository statusRepository;
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository,
                           ProductRepository productRepository,
                           CustomerRepository customerRepository,
                           StatusRepository statusRepository,
                           OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.statusRepository = statusRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping("/orders")
    public Order saveOrder(@RequestBody OrderRequestBody orb) {
        Order order = new Order(
                "ORD-1",
                customerRepository.getOne(orb.getCustomerId()),
                statusRepository.getOne(1),
                LocalDateTime.now()
        );

        Order saved = orderRepository.save(order);

        List<OrderDetail> orderDetails = new ArrayList<>();
        for(ProductQuantity pq : orb.getItems()) {
            Product product = productRepository.getOne(pq.getProductId());

            Integer currentStock = product.getStockQuantity();
            Integer newStock = currentStock - pq.getQuantity();
            product.setStockQuantity(newStock);
            productRepository.save(product);

            BigDecimal totalLine = product.getPrice().multiply(new BigDecimal(pq.getQuantity()));
            orderDetails.add( new OrderDetail(
                saved,
                product,
                pq.getQuantity(),
                totalLine
            ));
        }
        orderDetailRepository.saveAll(orderDetails);
        return saved;
    }
}
