package com.example.mgmgapp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
 * 商品情報を管理するエンティティクラス
 */
@Data
@Entity
@Table(name = "products")
public class Products {
    
    /**
     * 商品ID（主キー）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * 商品名（not null、最大30文字、一意）
     */
    @Column(name = "name", nullable = false, length = 30, unique = true)
    private String name;
    
    /**
     * 商品説明（最大200文字）
     */
    @Column(name = "description", length = 200)
    private String description;
    
    /**
     * 価格（not null、小数点以下2桁まで）
     */
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    /**
     * 在庫数（not null）
     */
    @Column(name = "stock", nullable = false)
    private Integer stock;
    
    /**
     * 商品画像のパス（not null）
     */
    @Column(name = "image_path", nullable = false)
    private String imagePath;
    
    /**
     * 商品カテゴリID（not null、一対一の関連）
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;
    
    /**
     * 作成日時
     */
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * 更新日時
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
