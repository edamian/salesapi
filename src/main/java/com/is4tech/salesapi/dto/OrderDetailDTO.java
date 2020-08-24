package com.is4tech.salesapi.dto;

import java.math.BigDecimal;

public class OrderDetailDTO {
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalLine;

    public OrderDetailDTO(String name, BigDecimal price, Integer quantity, BigDecimal totalLine) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.totalLine = totalLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalLine() {
        return totalLine;
    }

    public void setTotalLine(BigDecimal totalLine) {
        this.totalLine = totalLine;
    }
}
