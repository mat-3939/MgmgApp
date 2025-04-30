package com.example.mgmgapp.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mgmgapp.entity.Products;
import com.example.mgmgapp.repository.user.ProductRepository;

@Controller
public class ProductController {
	
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String showProducts(
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String order,
        Model model
    ) {
        // 並び順（昇順・降順）を指定
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);

        // ソート順に商品を取得
        List<Products> products = productRepository.findAll(sort);

        // 画面に渡す
        model.addAttribute("products", products);

        return "user/products";
    }
    
    //詳細ページへ
    @GetMapping("/products_detail/{id}")
    public String showProductDetail(@PathVariable("id") Integer id, Model model) {
        Products product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        model.addAttribute("product", product);
        return "user/products_detail";
    }
    
}
