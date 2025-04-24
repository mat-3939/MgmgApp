package com.example.mgmgapp.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mgmgapp.entity.Product;
import com.example.mgmgapp.service.user.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/producs")
@RequiredArgsConstructor
public class ProductController {
	//DI
	private final ProductService service;
	
	//一覧表示
	@GetMapping
	public String list(@RequestParam(value = "sort", defaultValue = "id_desc") String sort, Model model) {
	    // ソートを実行
	    model.addAttribute("products", service.findAllSorted(sort));
	    return "user/products";
	}
	
	//詳細表示
	@GetMapping("/{id}")
	public String detail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		//各情報の詳細を取得する
		Product p = service.findByIdProduct(id);
		if (p != null) {
			model.addAttribute("products", service.findByIdProduct(id));
			return "user/product_detail";
		}else {
			//対象が無いときは一覧ページにリダイレクト
			return "redirect:/products";
		}
	}
}
