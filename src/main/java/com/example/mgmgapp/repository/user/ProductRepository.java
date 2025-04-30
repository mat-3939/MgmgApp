package com.example.mgmgapp.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mgmgapp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
}
