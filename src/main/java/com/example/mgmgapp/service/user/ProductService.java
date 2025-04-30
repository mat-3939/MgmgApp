package com.example.mgmgapp.service.user;

import java.util.List;

import com.example.mgmgapp.entity.Products;

public interface ProductService {
	
	// ソート付き検索
    List<Products> findAllSorted(String sortBy);
	
	//該当するidのデータのみ検索
    Products findByIdProduct(Integer id);
}
