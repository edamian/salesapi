package com.is4tech.salesapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Size(min = 3, max = 90)
    @NotBlank(message = "First name is required")
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 3, max = 90)
    @NotBlank(message = "Last name is required")
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 70)
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(max = 15)
    @NotBlank(message = "Phone number is required")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Size(max = 150)
    @NotBlank(message = "Address is required")
    private String address;

    @Size(max = 32)
    @NotBlank(message = "Password is required")
    @Column(name = "pass")
    private String password;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private  LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders;

    @Column(name = "is_deleted")
    private Integer isDeleted;

    public Customer() { }

    public Customer(String firstName, String lastName, String email, String phoneNumber, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
    }

    public Customer(Integer id, @NotBlank(message = "First name is required") String firstName, @NotBlank(message = "Last name is required") String lastName, @NotBlank(message = "Email is required") String email, @NotBlank(message = "Phone number is required") String phoneNumber, @NotBlank(message = "Address is required") String address) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
