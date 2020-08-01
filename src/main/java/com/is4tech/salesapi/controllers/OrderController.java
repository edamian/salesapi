package com.is4tech.salesapi.controllers;

import com.is4tech.salesapi.models.*;
import com.is4tech.salesapi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RestController
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions( MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
