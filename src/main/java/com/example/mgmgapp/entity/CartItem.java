package com.example.mgmgapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "session_id", nullable = false, unique = true)
    private String session_id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product_id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    
}
