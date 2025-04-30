package com.example.mgmgapp.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);

        List<Products> products = productRepository.findAll(sort);
        model.addAttribute("products", products);

        return "user/products";
    }
}
