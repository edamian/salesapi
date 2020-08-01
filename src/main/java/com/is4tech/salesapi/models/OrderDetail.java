/*package com.is4tech.salesapi.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;
    @Column(name = "order_id")
    private Order orderId;
    @Column(name = "product_id")
    private Product productId;
    private Integer quantity;
    @Column(name = "total_line")
    private BigDecimal totalLine;

    public OrderDetail() {}

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
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

    @Override
    public String toString() {
        return "OrderDetail{" +
                "Id=" + Id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", totalLine=" + totalLine +
                '}';
    }
}
*/
