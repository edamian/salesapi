package com.is4tech.salesapi.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String name;
    private String description;
    private String image;
    @Column(name = "stock_quantity")
    private Integer stockQuantity;
    private BigDecimal price;
    private BigDecimal cost;
    @Column(name = "sale_price")
    private BigDecimal salePrice;

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
