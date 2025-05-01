// OrderItemRepository.java - 注文商品用リポジトリ
package com.example.mgmgapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mgmgapp.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
