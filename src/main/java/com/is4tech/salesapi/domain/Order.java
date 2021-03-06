package com.is4tech.salesapi.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "order_number")
    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    private BigDecimal total;

    private LocalDateTime dateCreated;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private  LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> details;

    @Column(name = "is_deleted")
    private Integer deleted;

    public Order() {}

    public Order(String orderNumber, Customer customer, Status status, LocalDateTime dateCreated, BigDecimal total) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.status = status;
        this.dateCreated = dateCreated;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer isDeleted() {
        return deleted;
    }

    public void setDeleted(Integer isDeleted) {
        this.deleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Order{" +
                "Id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", customer=" + customer +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
