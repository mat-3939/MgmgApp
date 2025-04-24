package com.example.mgmgapp.repository.user;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.mgmgapp.entity.Product;

public interface ProductRepository {
	// すべての一覧データを表示（ソート前提）
	List<Product> selectAllSorted(@Param("sortBy") String sortBy);
	
	//指定されたIDのProductを取得
	Product selectById(@Param("id")Integer id);
}
