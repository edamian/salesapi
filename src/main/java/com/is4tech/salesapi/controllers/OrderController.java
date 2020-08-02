package com.is4tech.salesapi.controllers;

import com.is4tech.salesapi.models.*;
import com.is4tech.salesapi.repositories.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final StatusRepository statusRepository;
    private final OrderDetailRepository orderDetailRepository;

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
        LocalDateTime placementDate = LocalDateTime.now(ZoneId.of("America/Guatemala"));
        Customer customer = customerRepository.getOne(orb.getCustomerId());
        Status status = statusRepository.getOne(1);
        String hash = DigestUtils.sha1Hex(placementDate.toString() + "-" + customer.getEmail());
        String orderNumber = Year.now(ZoneId.of("America/Guatemala")).getValue() + "-" + hash.substring(0,8);

        Order order = new Order(
                orderNumber,
                customer,
                status,
                placementDate,
                BigDecimal.ZERO
        );

        Order saved = orderRepository.save(order);

        List<OrderDetail> orderDetails = new ArrayList<>();
        BigDecimal totalOrder = BigDecimal.ZERO;

        for(ProductQuantity pq : orb.getItems()) {
            Product product = productRepository.getOne(pq.getProductId());

            Integer currentStock = product.getStockQuantity();
            Integer newStock = currentStock - pq.getQuantity();
            product.setStockQuantity(newStock);
            productRepository.save(product);

            BigDecimal totalLine = product.getPrice().multiply(new BigDecimal(pq.getQuantity()));
            totalOrder = totalOrder.add(totalLine);
            orderDetails.add( new OrderDetail(
                saved,
                product,
                pq.getQuantity(),
                totalLine
            ));
        }
        order.setTotal(totalOrder);
        orderRepository.save(order);
        orderDetailRepository.saveAll(orderDetails);
        return saved;
    }
}
