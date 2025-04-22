package com.example.mgmgapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "products")
public class Product {
    
    // 商品ID（主キー）
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    // 商品名（必須、最大200文字、一意）
    @Column(name = "name", nullable = false, length = 200, unique = true)
    private String name;
    
    // 商品説明（最大200文字）
    @Column(name = "description", length = 200)
    private String description;
    
    // 価格（必須、小数点以下2桁まで）
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    // 在庫数（必須）
    @Column(name = "stock", nullable = false)
    private Integer stock;
    
    // 商品画像のパス（必須）
    @Column(name = "image_path", nullable = false)
    private String imagePath;
    
    // 商品カテゴリID（必須、一対一の関連）
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category_id;
    
    // 作成日時（必須）
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    // 更新日時（必須）
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
