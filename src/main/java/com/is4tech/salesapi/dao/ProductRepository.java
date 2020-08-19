package com.is4tech.salesapi.dao;

import com.is4tech.salesapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
