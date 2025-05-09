package com.example.mgmgapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mgmgapp.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer>{
	
//	 // idに一致する商品の価格だけを取得
//    @Query("SELECT p.price FROM Products p WHERE p.id = :id")
//    Integer findPriceById(@Param("id") Integer id);
}
