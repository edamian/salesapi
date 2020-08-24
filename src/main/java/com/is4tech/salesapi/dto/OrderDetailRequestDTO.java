package com.is4tech.salesapi.dto;

import javax.validation.constraints.NotEmpty;

public class OrderDetailRequestDTO {
    @NotEmpty
    private Integer productId;
    @NotEmpty
    private Integer quantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
