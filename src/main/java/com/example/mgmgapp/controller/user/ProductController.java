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
//@RequestMapping("/products")
//@RequiredArgsConstructor
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//DI
	/*private final ProductService productService;
	
	
	//一覧表示
	@GetMapping
	public String list(@RequestParam(value = "sort", defaultValue = "id_desc") String sort, Model model) {
	    // ソートを実行
	    model.addAttribute("products", productService.findAllSorted(sort));
	    return "user/products";
	}
	
	//詳細表示
	@GetMapping("/{id}")
	public String detail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		//各情報の詳細を取得する
		Products p = productService.findByIdProduct(id);
		if (p != null) {
			model.addAttribute("products", productService.findByIdProduct(id));
			return "user/product_detail";
		}else {
			//対象が無いときは一覧ページにリダイレクト
			return "redirect:/products";
		}
	}*/
}
