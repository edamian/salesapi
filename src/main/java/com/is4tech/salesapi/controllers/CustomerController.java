package com.is4tech.salesapi.controllers;

import com.is4tech.salesapi.models.Customer;
import com.is4tech.salesapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository dependency) {
        this.customerRepository = dependency;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() { return  customerRepository.findAll(); }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        Integer customerId = Integer.parseInt(id);
        return customerRepository.getOne(customerId);
    }

    @PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

}
