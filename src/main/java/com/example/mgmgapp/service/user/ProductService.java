package com.example.mgmgapp.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.mgmgapp.entity.Products;
import com.example.mgmgapp.repository.user.ProductRepository;

@Service
public class ProductService {
    
    // ProductRepository をインジェクト
    @Autowired
    private ProductRepository productRepository;
    
    // ソートされた商品一覧を返すメソッド
    public List<Products> getAllProductsSorted(String sort) {
        switch (sort) {
            case "priceAsc":
                return productRepository.findAll(Sort.by(Sort.Order.asc("price"))); // 値段が安い順
            case "priceDesc":
                return productRepository.findAll(Sort.by(Sort.Order.desc("price"))); // 値段が高い順
            case "idDesc":
                return productRepository.findAll(Sort.by(Sort.Order.desc("id"))); // IDが大きい順
            default:
                return productRepository.findAll(); // デフォルトで全件返す
        }
    }
}
