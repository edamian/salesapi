package com.is4tech.salesapi.repositories;

import com.is4tech.salesapi.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
