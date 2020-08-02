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

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable String id, @Valid @RequestBody Customer customer) {
        Integer customerId = Integer.parseInt(id);
        Customer tmp = customerRepository.getOne(customerId);
        tmp.setFirstName(customer.getFirstName());
        tmp.setLastName(customer.getLastName());
        tmp.setEmail(customer.getEmail());
        tmp.setPhoneNumber(customer.getPhoneNumber());
        tmp.setAddress(customer.getAddress());
        tmp.setPassword(customer.getPassword());
        return customerRepository.save(customer);
    }
}
