package com.example.mgmgapp.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mgmgapp.entity.Products;
import com.example.mgmgapp.repository.user.ProductRepository;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;
    
	@Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
 // ソートされた商品一覧を返すメソッド
//    public List<Products> getAllProductsSorted(String sortBy, String order) {
//        // 並び順（昇順・降順）を指定
//        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
//
//        // ソート対象（sortBy）に応じてソートを実行
//        switch (sortBy) {
//            case "price":
//                return productRepository.findAll(Sort.by(direction, "price")); // 価格でソート
//            case "id":
//                return productRepository.findAll(Sort.by(direction, "id")); // IDでソート
//            default:
//                return productRepository.findAll(); // ソートなし
//        }
//    }
    
    public List<Products> getSortedProducts(String sortKey) {
        return switch (sortKey) {
            case "priceAsc" -> productRepository.findAllByOrderByPriceAsc();
            case "priceDesc" -> productRepository.findAllByOrderByPriceDesc();
            default -> productRepository.findAllByOrderByCreatedAtDesc(); // "new" またはデフォルト
        };
    }
    
    public Products getProductById(Integer id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
    }
    
    // カテゴリIDに応じた画像パスを作成
    public String buildImagePath(Products p) {
        String categoryName;
        int categoryId = p.getCategory().getId(); // CategoriesエンティティからIDを取得

        switch (categoryId) {
            case 1:
                categoryName = "washoku";
                break;
            case 2:
                categoryName = "yoshoku";
                break;
            case 3:
                categoryName = "chuka";
                break;
            default:
                categoryName = "other";
                break;
        }

        return "/img/" + categoryName + "/" + p.getImagePath();
    }
}
