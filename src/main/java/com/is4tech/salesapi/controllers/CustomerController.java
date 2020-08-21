package com.is4tech.salesapi.controllers;

import com.is4tech.salesapi.domain.Customer;
import com.is4tech.salesapi.services.CustomerService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Resource created"),
    @ApiResponse(responseCode = "400", description = "Invalid request"),
    @ApiResponse(responseCode = "404", description = "Resource not found"),
})
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/customers/{id}", produces = "application/json")
    @Timed("get.costumer.by.id")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        try {
            Integer customerId = Integer.parseInt(id);
            return ResponseEntity.ok(customerService.getById(customerId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/customers", produces = "application/json")
    @Timed("save.costumer")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        try {
            return ResponseEntity.ok(customerService.save(customer));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
