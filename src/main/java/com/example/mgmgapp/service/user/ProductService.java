package com.example.mgmgapp.service.user;

import java.util.List;

import com.example.mgmgapp.entity.Product;

public interface ProductService {
	
	// ソート付き検索
    List<Product> findAllSorted(String sortBy);
	
	//該当するidのデータのみ検索
    Product findByIdProduct(Integer id);
}
