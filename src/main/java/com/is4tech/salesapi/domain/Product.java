package com.is4tech.salesapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "description is required")
    private String description;
    @NotBlank(message = "image is required")
    private String image;
    @NotNull(message = "stockQuantity is required")
    @Column(name = "stock_quantity")
    private Integer stockQuantity;
    @NotNull(message = "price is required")
    private BigDecimal price;
    @NotNull(message = "cost is required")
    private BigDecimal cost;
    @NotNull(message = "salePrice is required")
    @Column(name = "sale_price")
    private BigDecimal salePrice;
    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;
    @Column(name = "modified_date")
    @LastModifiedDate
    private  LocalDateTime modifiedDate;
    @OneToMany(mappedBy = "productId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderDetail> details;

    @Column(name = "is_deleted")
    private Integer isDeleted;

    public  Product() {}

    public Product(String name, String description, String image, Integer stockQuantity, BigDecimal price, BigDecimal cost, BigDecimal salePrice) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.cost = cost;
        this.salePrice = salePrice;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", price=" + price +
                ", cost=" + cost +
                ", salePrice=" + salePrice +
                '}';
    }
}
