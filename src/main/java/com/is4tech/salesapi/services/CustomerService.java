package com.is4tech.salesapi.services;

import com.is4tech.salesapi.dao.CustomerRepository;
import com.is4tech.salesapi.domain.Customer;
import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getById(Integer id) {
        return customerRepository.getOne(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

}
