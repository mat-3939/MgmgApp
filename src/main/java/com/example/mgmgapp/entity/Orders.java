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

/**
 * 注文情報を管理するエンティティクラス
 */
@Data
@Entity
@Table(name = "orders")
public class Orders {

    /**
     * 注文ID（主キー）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名（not null）
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * 姓（not null）
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * メールアドレス（not null）
     */
    @Column(name = "email", nullable = false)
    private String email;
    
    /**
     * 郵便番号（not null）
     */
    @Column(name = "postcode", nullable = false)
    private String postcode;

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
    private String tel;

    /**
     * 支払い方法（not null）
     */
    @Column(name = "payment_method", nullable = false, length = 10)
    private String paymentMethod;

    /**
     * 合計金額（not null、小数点以下2桁まで）
     */
    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;
    
    /**
     * 注文作成日時
     */
    @Column(name = "order_date")
    private LocalDateTime orderDate;

    /**
     * 注文ステータス（not null）
     */
    @Column(name = "status", nullable = false)
    private Boolean status;
}