package com.is4tech.salesapi.repositories;

import com.is4tech.salesapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
