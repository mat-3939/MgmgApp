package com.example.mgmgapp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_name", nullable = false)
    private String customer_name;

    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "postcode", nullable = false)
    private String postcode;

    @Column(name = "address", nullable = false)
    private String address;
    
    @Column(name = "tel", nullable = false)
    private String tel;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal total_price;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

}
