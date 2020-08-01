package com.is4tech.salesapi.controllers;

import com.is4tech.salesapi.models.Product;
import com.is4tech.salesapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository dependency) { this.productRepository = dependency; }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String id) {
        return productRepository.getOne(Integer.parseInt(id));
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Map<String, String> body) {
        Product tempProduct = new Product(
            body.get("name"),
            body.get("description"),
            body.get("image"),
            Integer.parseInt(body.get("stock_quantity")),
            new BigDecimal(body.get("price")),
            new BigDecimal(body.get("cost")),
            new BigDecimal(body.get("sale_price"))
        );
        return productRepository.save(tempProduct);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Map<String, String> body) {
        Integer productId = Integer.parseInt(id);
        Product temp = productRepository.getOne(productId);
        temp.setName(body.get("name"));
        temp.setDescription(body.get("description"));
        temp.setImage(body.get("image"));
        temp.setStockQuantity(Integer.parseInt(body.get("stock_quantity")));
        temp.setPrice(new BigDecimal(body.get("price")));
        temp.setCost(new BigDecimal(body.get("cost")));
        temp.setSalePrice(new BigDecimal(body.get("sale_price")));
        return productRepository.save(temp);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable String id) {
        productRepository.deleteById(Integer.parseInt(id));
    }

}
