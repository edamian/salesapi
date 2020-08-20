package com.is4tech.salesapi.controllers;

import com.is4tech.salesapi.domain.Product;
import com.is4tech.salesapi.services.ProductService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Resource created"),
    @ApiResponse(responseCode = "204", description = "No content"),
    @ApiResponse(responseCode = "400", description = "Invalid request"),
    @ApiResponse(responseCode = "404", description = "Resource not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) { this.productService = productService; }

    @GetMapping(value = "/products", produces = "application/json")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            return ResponseEntity.ok(productService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(productService.getById(Integer.parseInt(id)));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/products", produces = "application/json")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.save(product));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @Valid @RequestBody Product product) {
        try {
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
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        try {
            productService.deleteById(Integer.parseInt(id));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
