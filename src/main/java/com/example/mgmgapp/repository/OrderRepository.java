// OrderRepository.java - 注文情報用リポジトリ
package com.example.mgmgapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mgmgapp.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}