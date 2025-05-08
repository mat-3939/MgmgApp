package com.example.mgmgapp.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mgmgapp.entity.Products;
import com.example.mgmgapp.service.user.ProductService;

@Controller
public class ProductController {
	
    @Autowired
    private ProductService productService; 

    @GetMapping("/products")
    public String showProducts(
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String order,
        Model model
    ) {
        if (sortBy.equals("idDesc")) {
            sortBy = "id";
            order = "desc";
        } else if (sortBy.equals("priceAsc")) {
            sortBy = "price";
            order = "asc";
        } else if (sortBy.equals("priceDesc")) {
            sortBy = "price";
            order = "desc";
        }

        // ソートされた順で取得
        List<Products> products = productService.getAllProductsSorted(sortBy, order);

        // 商品ごとの画像パスをMapで構築
        Map<Integer, String> imagePaths = products.stream()
            .collect(Collectors.toMap(
                Products::getId,
                productService::buildImagePath
            ));

        model.addAttribute("products", products);
        model.addAttribute("imagePaths", imagePaths);

        return "user/products";
    }
    
    //詳細ページへ
    @GetMapping("/products_detail/{id}")
    public String showProductDetail(@PathVariable("id") Integer id, Model model) {
        Products product = productService.getProductById(id);

        // 画像パスのMapを1件分作成
        Map<Integer, String> imagePaths = new HashMap<>();
        imagePaths.put(product.getId(), productService.buildImagePath(product));

        model.addAttribute("product", product);
        model.addAttribute("imagePaths", imagePaths);

        return "user/products_detail";
    }
}
