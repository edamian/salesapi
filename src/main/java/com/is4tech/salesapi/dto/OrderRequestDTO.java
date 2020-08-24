package com.is4tech.salesapi.dto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class OrderRequestDTO {

    @NotEmpty(message = "customerId is required")
    private Integer customerId;
    @NotEmpty(message = "items list is required")
    private List<OrderDetailRequestDTO> items;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<OrderDetailRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderDetailRequestDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderRequestBody{" +
                "customerId=" + customerId +
                '}';
    }
}
