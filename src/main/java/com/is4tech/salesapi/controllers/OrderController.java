package com.is4tech.salesapi.controllers;

import com.is4tech.salesapi.domain.*;
import com.is4tech.salesapi.dto.OrderDTO;
import com.is4tech.salesapi.services.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Resource created"),
    @ApiResponse(responseCode = "400", description = "Invalid request"),
    @ApiResponse(responseCode = "404", description = "Resource not found"),
})
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

    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            return ResponseEntity.ok(orderService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping(value = "/orders/{id}", produces = "application/json")
    public ResponseEntity<OrderDTO> getById(@PathVariable String id) {
        Integer orderId = Integer.parseInt(id);
        Order order = orderService.getById(orderId);
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);
        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<Order> saveOrder(@RequestBody OrderRequestBody orb) {
        try {
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
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
