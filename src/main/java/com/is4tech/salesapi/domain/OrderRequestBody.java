package com.is4tech.salesapi.domain;

import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderRequestBody {

    @NotNull(message = "customerId is required")
    private Integer customerId;
    @NotNull(message = "items list is required")
    private List<ProductQuantity> items;

    public OrderRequestBody() {}

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<ProductQuantity> getItems() {
        return items;
    }

    public void setItems(List<ProductQuantity> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderRequestBody{" +
                "customerId=" + customerId +
                '}';
    }
}
