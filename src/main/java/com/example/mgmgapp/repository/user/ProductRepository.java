package com.example.mgmgapp.repository.user;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mgmgapp.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Integer>{
	
	// 商品名に部分一致するものを検索（大文字小文字を無視）
    List<Products> findByNameContainingIgnoreCase(String name);
	
	/**
     * 指定したカテゴリの商品一覧を取得
     */
    List<Products> findByCategoryIdOrderByCreatedAtDesc(Integer categoryId);
	
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
    
    /**
     * カテゴリ指定＆ソート条件
     */
    List<Products> findByCategoryId(Integer categoryId, Sort sort);
    
    /**
     * 指定した商品IDのリストで複数の商品を取得
     */
    List<Products> findByIdIn(List<Integer> ids);
    
}
