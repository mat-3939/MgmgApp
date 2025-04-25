package com.example.mgmgapp.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mgmgapp.entity.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminOrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
