package com.example.mgmgapp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

@Data // Lombokが自動でgetter/setter、toString、equals、hashCodeを生成
@Entity // JPAによってこのクラスがエンティティ（テーブルに対応）であることを示す
@Table(name = "orders") // このエンティティが「orders」テーブルに対応していることを指定
public class Order {

    @Id // 主キー（Primary Key）を表す
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    // 主キーをDB側で自動生成（PostgreSQLならSERIALなど）
    private Integer id; // 注文ID（自動採番）

    @Column(name = "email", nullable = false)
    private String email; // ユーザーのメールアドレス（確認メール送信用）

    @Column(name = "first_name", nullable = false)
    private String firstName; // ユーザーの名（下の名前）

    @Column(name = "last_name", nullable = false)
    private String lastName; // ユーザーの姓（苗字）

    @Column(name = "postcode", nullable = false)
    private String postcode; // 郵便番号（配送先の特定に使用）


    /**
     * 都道府県（not null）
     */
    @Column(name = "prefecture", nullable = false)
    private String prefecture;

    /**
     * 市区町村（not null）
     */
    @Column(name = "city", nullable = false)
    private String city;

    /**
     * 住所（not null）
     */
    @Column(name = "address_line", nullable = false)
    private String addressLine;

    /**
     * 建物名（null）
     */
    @Column(name = "building", nullable = true)
    private String building;
    
    /**
     * 電話番号（not null）
     */

    @Column(name = "tel", nullable = false)
    private String tel; // 電話番号（緊急連絡や配送時の連絡に使用）

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice; 
    // 合計金額（商品ごとの合計ではなく、カート全体の購入金額）

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate; 
    // 注文日時（注文が確定されたタイムスタンプ）

    @Column(name = "status", nullable = false)
    private Boolean status; 
    // 注文の状態（true: 発送済み / false: 未発送など、アプリによって運用ルールを決定）

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    // この注文に関連する複数の注文商品(OrderItem)を表す（親子関係）
    // cascade = ALL によって、Orderを保存・削除すると紐づくOrderItemも一緒に処理される
    private List<OrderItem> orderItems; 
    // 注文内の商品リスト（1注文に対して複数の商品を持つ）
}

