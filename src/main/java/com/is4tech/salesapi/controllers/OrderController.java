package com.is4tech.salesapi.controllers;

import com.is4tech.salesapi.models.*;
import com.is4tech.salesapi.services.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;
    private final CustomerService customerService;
    private final StatusService statusService;
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderController(OrderService orderService,
                           ProductService productService,
                           CustomerService customerService,
                           StatusService statusService,
                           OrderDetailService orderDetailService) {
        this.orderService = orderService;
        this.productService = productService;
        this.customerService = customerService;
        this.statusService = statusService;
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> saveOrder(@RequestBody OrderRequestBody orb) {
        LocalDateTime placementDate = LocalDateTime.now(ZoneId.of("America/Guatemala"));
        Customer customer = customerService.getById(orb.getCustomerId());
        Status status = statusService.getById(1);
        String hash = DigestUtils.sha1Hex(placementDate.toString() + "-" + customer.getEmail());
        String orderNumber = Year.now(ZoneId.of("America/Guatemala")).getValue() + "-" + hash.substring(0,8);

        Order order = new Order(
                orderNumber,
                customer,
                status,
                placementDate,
                BigDecimal.ZERO
        );

        Order saved = orderService.save(order);

        List<OrderDetail> orderDetails = new ArrayList<>();
        BigDecimal totalOrder = BigDecimal.ZERO;

        for(ProductQuantity pq : orb.getItems()) {
            Product product = productService.getById(pq.getProductId());

            Integer currentStock = product.getStockQuantity();
            Integer newStock = currentStock - pq.getQuantity();
            product.setStockQuantity(newStock);
            productService.save(product);

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
        orderService.save(order);
        orderDetailService.saveAll(orderDetails);
        return ResponseEntity.ok(saved);
    }
}
