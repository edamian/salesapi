package com.is4tech.salesapi.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;
    private String name;
    private String description;
    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;
    @Column(name = "modified_date")
    @LastModifiedDate
    private  LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<Order> orders;

    public Status() {}

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

    @Override
    public String toString() {
        return "Status{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
