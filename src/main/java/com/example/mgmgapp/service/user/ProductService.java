package com.example.mgmgapp.service.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mgmgapp.entity.Products;

public interface ProductService extends JpaRepository<Products, Integer>{
	
	// ソート付き検索
    //List<Products> findAllSorted(String sortBy);
	
	//該当するidのデータのみ検索
    //Products findByIdProduct(Integer id);
}
