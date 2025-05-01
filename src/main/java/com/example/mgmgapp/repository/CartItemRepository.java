package com.example.mgmgapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mgmgapp.entity.CartItems;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Integer>{
	
	List<CartItems> findBySessionId(String sessionId);
	
}
