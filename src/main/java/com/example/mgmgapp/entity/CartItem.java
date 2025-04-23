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

/**
 * カート内の商品情報を管理するエンティティクラス
 */
@Data
@Entity
@Table(name = "cart_items")
public class CartItem {

    /**
     * カートアイテムID（主キー）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * セッションID（not null、一意）
     */
    @Column(name = "session_id", nullable = false, unique = true)
    private String session_id;

    /**
     * 商品ID（not null、一意、多対一の関連）
     */
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product_id;

    /**
     * 商品数量（not null）
     */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    
}
