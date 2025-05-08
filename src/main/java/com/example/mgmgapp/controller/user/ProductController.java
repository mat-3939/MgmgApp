package com.example.mgmgapp.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mgmgapp.entity.Product;
import com.example.mgmgapp.repository.user.ProductRepository;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired // コンストラクタ注入（@Autowiredは省略可能）
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String listProducts(@RequestParam(name = "q", required = false) String query, Model model) {
        List<Product> products;
        
        if (query != null && !query.isEmpty()) {
            products = productRepository.findByNameEqualsIgnoreCase(query);
         // 完全一致した商品が1件だけの場合は詳細ページへリダイレクト
            if (products.size() == 1) {
                Long productId = products.get(0).getId();
                return "redirect:/user/products/" + productId;
            }
            // 複数一致または0件：そのまま検索結果として一覧表示
        } else {
            products = productRepository.findAll();
        }
        model.addAttribute("products", products);
        model.addAttribute("searchQuery", query);
        return "products/list";
    }
    
        @GetMapping("/products/{id}")
        public String showProductDetail(@PathVariable Long id, Model model) {
            Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("商品が見つかりません"));
            model.addAttribute("product", product);
            return "/user/products/detail"; // 例: templates/user/products/detail.html
        
        
    }
}
