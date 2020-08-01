package com.is4tech.salesapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;
    private String orderNumber;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "status_id")
    private Status status;
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private List<OrderDetail> details;

    public Order() {}

    public Order(String orderNumber, Customer customer, Status status, LocalDateTime dateCreated) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.status = status;
        this.dateCreated = dateCreated;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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

    @Override
    public String toString() {
        return "Order{" +
                "Id=" + Id +
                ", orderNumber='" + orderNumber + '\'' +
                ", customer=" + customer +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
