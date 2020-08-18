package com.is4tech.salesapi.services;

import com.is4tech.salesapi.models.Product;
import com.is4tech.salesapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product getById(Integer id) {
        return productRepository.getOne(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Integer id) {
        Product product = productRepository.getOne(id);
        product.setIsDeleted(1);
        productRepository.save(product);
    }
}
