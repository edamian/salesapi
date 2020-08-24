package com.is4tech.salesapi.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class CustomerDTO {

    private Integer id;

    @Size(min = 3, max = 90)
    @NotBlank(message = "First name is required")
    private String firstName;

    @Size(min = 3, max = 90)
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Size(max = 70)
    @Email(message = "Invalid email address")
    private String email;

    @Size(max = 15)
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @Size(max = 150)
    @NotBlank(message = "Address is required")
    private String address;

    @Size(max = 32)
    @NotBlank(message = "Password is required")
    private String password;

    private List<OrderDTO> orders;

    @Column(name = "is_deleted")
    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
