package com.example.mgmgapp.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mgmgapp.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Integer>{
	
	/**
     * 登録日が新しい順（降順）
     */
    List<Products> findAllByOrderByCreatedAtDesc();
	
	/**
     * 価格が高い順（降順）
     */
    List<Products> findAllByOrderByPriceDesc();
	
	/**
     * 価格が安い順（昇順）
     */
    List<Products> findAllByOrderByPriceAsc();
    
    
}
