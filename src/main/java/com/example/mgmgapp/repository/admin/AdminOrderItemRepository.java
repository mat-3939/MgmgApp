package com.example.mgmgapp.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mgmgapp.entity.OrderItems;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminOrderItemRepository extends JpaRepository<OrderItems, Integer> {

}
