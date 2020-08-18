package com.is4tech.salesapi.controllers;

import com.is4tech.salesapi.models.Product;
import com.is4tech.salesapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) { this.productService = productService; }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getById(Integer.parseInt(id)));
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @Valid @RequestBody Product product) {
        Integer productId = Integer.parseInt(id);
        Product temp = productService.getById(productId);
        temp.setName(product.getName());
        temp.setDescription(product.getDescription());
        temp.setImage(product.getImage());
        temp.setStockQuantity(product.getStockQuantity());
        temp.setPrice(product.getPrice());
        temp.setCost(product.getCost());
        temp.setSalePrice(product.getSalePrice());
        return ResponseEntity.ok(productService.save(temp));
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteById(Integer.parseInt(id));
    }
}
