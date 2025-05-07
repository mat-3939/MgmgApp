package com.example.mgmgapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data // Lombokが自動でgetter/setter、toString、equals、hashCodeを生成
@Entity // JPAのエンティティ（データベースのテーブルに対応）
@Table(name = "order_items") // 対応するテーブル名を明示
public class OrderItem {

    @Id // 主キー
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 注文商品ID（自動採番）

    @ManyToOne // 多対一のリレーション：複数のOrderItemが1つのOrderに属する
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // 紐づく親注文（order_items.order_id → orders.id）

    @Column(name = "product_name", nullable = false)
    private String productName; // 商品名（商品IDと分けて保存。履歴保持のため）

    @Column(name = "quantity", nullable = false)
    private Integer quantity; // 注文数量（1以上）

    @Column(name = "price", nullable = false)
    private BigDecimal price; // 単価（注文時点の金額で保存）

    // 合計金額は単価×数量で算出可能なため、あえて保存していない（冗長性回避）
}
