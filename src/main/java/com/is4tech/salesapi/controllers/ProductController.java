package com.is4tech.salesapi.controllers;

import com.is4tech.salesapi.models.Product;
import com.is4tech.salesapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository dependency) { this.productRepository = dependency; }

    @GetMapping("/products")
    public List<Product> getAllProducts() { return productRepository.findAll(); }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String id) { return productRepository.getOne(Integer.parseInt(id)); }

    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable String id, @Valid @RequestBody Product product) {
        Integer productId = Integer.parseInt(id);
        Product temp = productRepository.getOne(productId);
        temp.setName(product.getName());
        temp.setDescription(product.getDescription());
        temp.setImage(product.getImage());
        temp.setStockQuantity(product.getStockQuantity());
        temp.setPrice(product.getPrice());
        temp.setCost(product.getCost());
        temp.setSalePrice(product.getSalePrice());
        return productRepository.save(temp);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable String id) {
        productRepository.deleteById(Integer.parseInt(id));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
