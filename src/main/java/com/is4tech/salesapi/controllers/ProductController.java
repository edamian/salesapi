package com.is4tech.salesapi.controllers;

import com.is4tech.salesapi.domain.Product;
import com.is4tech.salesapi.dto.ProductDTO;
import com.is4tech.salesapi.services.ProductService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.BeanUtils;
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
    @Timed("get.products")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            return ResponseEntity.ok(productService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping(value = "/products/{id}", produces = "application/json")
    @Timed("get.product.by.id")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(productService.getById(Integer.parseInt(id)));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/products", produces = "application/json")
    @Timed("save.product")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO product) {
        try {
            Product saved = new Product();
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, saved);
            saved = productService.save(saved);
            BeanUtils.copyProperties(saved, productDTO);
            return ResponseEntity.ok(productDTO);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/products/{id}", produces = "application/json")
    @Timed("update.product")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @Valid @RequestBody ProductDTO productDTO) {
        try {
            Integer productId = Integer.parseInt(id);
            Product temp = productService.getById(productId);
            BeanUtils.copyProperties(productDTO, temp);
            /*temp.setName(productDTO.getName());
            temp.setDescription(productDTO.getDescription());
            temp.setImage(productDTO.getImage());
            temp.setStockQuantity(productDTO.getStockQuantity());
            temp.setPrice(productDTO.getPrice());
            temp.setCost(productDTO.getCost());
            temp.setSalePrice(productDTO.getSalePrice());*/
            productService.save(temp);

            System.out.println(temp);
            System.out.println(productDTO);

            return ResponseEntity.ok(productDTO);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/products/{id}")
    @Timed("delete.product")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        try {
            productService.deleteById(Integer.parseInt(id));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
