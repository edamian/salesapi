package com.is4tech.salesapi.controllers;

import com.is4tech.salesapi.models.Customer;
import com.is4tech.salesapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
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
    public Customer createCustomer(@RequestBody Map<String, String> body) {
        Customer newCustomer = new Customer(
                body.get("first_name"),
                body.get("last_name"),
                body.get("email"),
                body.get("phone_number"),
                body.get("address"),
                body.get("password")
        );
        return customerRepository.save(newCustomer);
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable String id, @RequestBody Map<String, String> body) {
        Integer customerId = Integer.parseInt(id);
        Customer customer = customerRepository.getOne(customerId);
        customer.setFirstName(body.get("first_name"));
        customer.setLastName(body.get("last_name"));
        customer.setEmail(body.get("email"));
        customer.setPhoneNumber(body.get("phone_number"));
        customer.setAddress(body.get("address"));
        customer.setPassword(body.get("password"));
        return customerRepository.save(customer);
    }
}
