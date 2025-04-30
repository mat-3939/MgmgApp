package com.example.mgmgapp.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mgmgapp.entity.Product;

@Service
public interface ProductService {
	
	// ソート付き検索
    List<Product> findAllSorted(String sortBy);
	
	//該当するidのデータのみ検索
    Product findByIdProduct(Integer id);
}
