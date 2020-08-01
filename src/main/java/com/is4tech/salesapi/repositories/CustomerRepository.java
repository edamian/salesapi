package com.is4tech.salesapi.repositories;

import com.is4tech.salesapi.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
