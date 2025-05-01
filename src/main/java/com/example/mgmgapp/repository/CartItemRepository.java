package com.example.mgmgapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mgmgapp.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
	
	List<CartItem> findBySessionId(int sessionId);
	
}
