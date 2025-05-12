package com.example.mgmgapp.controller.user;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mgmgapp.entity.Products;
import com.example.mgmgapp.service.user.CategoryService;
import com.example.mgmgapp.service.user.ProductService;

@Controller
public class ProductController {
	
	private final ProductService productService;
	private final CategoryService categoryService;
	
	@Autowired
	public ProductController(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	} 
	
	@GetMapping("/")
	public String goHome(Model model) {
		//PICK UP商品を表示
        List<Integer> ids = Arrays.asList(1, 6, 8, 11, 3); //任意の商品IDをセット
        List<Products> pickUp = productService.getProductsByIds(ids);
        
        model.addAttribute("pickUp", pickUp);
        
		return "user/index";
	}

    @GetMapping("/products")
    public String showProducts(
//        @RequestParam(defaultValue = "id") String sortBy,
//        @RequestParam(defaultValue = "asc") String order,
    	  @RequestParam(required = false) Integer categoryId,
    	  @RequestParam(required = false, defaultValue = "new") String sort,
        Model model
    ) {
//        if (sortBy.equals("idDesc")) {
//            sortBy = "id";
//            order = "desc";
//        } else if (sortBy.equals("priceAsc")) {
//            sortBy = "price";
//            order = "asc";
//        } else if (sortBy.equals("priceDesc")) {
//            sortBy = "price";
//            order = "desc";
//        }
    	
    	List<Products> products;
    	
    	// フィルタリング条件の分岐
    	if (categoryId != null) {
    		products = productService.findByCategoryIdSorted(categoryId, sort);
    	} else {
    		products = productService.getSortedProducts(sort);
    	}

        // ソートされた順で取得
//        List<Products> products = productService.getSortedProducts(sort);

        // 商品ごとの画像パスをMapで構築
//        Map<Integer, String> imagePaths = products.stream()
//            .collect(Collectors.toMap(
//                Products::getId,
//                productService::buildImagePath
//            ));

        model.addAttribute("products", products);
//        model.addAttribute("imagePaths", imagePaths);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("sort", sort);

        return "user/products";
    }
    
    /**
     * キーワード検索＆カテゴリ絞り込み
     */
    @GetMapping("/search")
    public String searchProducts(@RequestParam(value = "q", required = false) String query,
                                 @RequestParam(value = "category", required = false) Integer categoryId,
                                 Model model) {
        
        List<Products> results;

        if ((query == null || query.isEmpty()) && categoryId != null) {
            // キーワードなしでカテゴリだけ指定 → カテゴリ絞り込みのみ
            results = productService.findByCategoryId(categoryId);
        } else if (query != null && !query.isEmpty() && categoryId != null) {
            // 両方指定 → 両方で絞り込み
            results = productService.searchByNameAndCategory(query, categoryId);
        } else if (query != null && !query.isEmpty()) {
            // キーワードだけ指定
            results = productService.searchByName(query);
        } else {
            // どちらも指定なし → 全件表示またはリダイレクト
            return "redirect:/products";
        }

        if (results.size() == 1) {
            return "redirect:/products_detail/" + results.get(0).getId();
        } else if (results.isEmpty()) {
            return "redirect:/products";
        } else {
            model.addAttribute("products", results);
            model.addAttribute("searchQuery", query);
            model.addAttribute("selectedCategoryId", categoryId);
            model.addAttribute("categories", categoryService.findAll());
            return "user/products";
        }
    }
    
    /**
	 * 指定したカテゴリの商品一覧を表示
	 */
	@GetMapping("/products/category/{categoryId}")
	public String listByCategory(@PathVariable Integer categoryId, Model model) {
		List<Products> products = productService.findByCategoryId(categoryId);
		model.addAttribute("products", products);
		model.addAttribute("searchQuery", null);
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("selectedCategoryId", categoryId);
		return "user/products";
	}
    
    //詳細ページへ
    @GetMapping("/products_detail/{id}")
    public String showProductDetail(@PathVariable("id") Integer id, Model model) {
        Products product = productService.getProductById(id);

        // 画像パスのMapを1件分作成
//        Map<Integer, String> imagePaths = new HashMap<>();
//        imagePaths.put(product.getId(), productService.buildImagePath(product));
        
        //PICK UP商品を表示
        List<Integer> ids = Arrays.asList(1, 6, 8, 11, 3); //任意の商品IDをセット
        List<Products> pickUp = productService.getProductsByIds(ids);

        model.addAttribute("product", product);
        model.addAttribute("pickUp", pickUp);
//        model.addAttribute("imagePaths", imagePaths);

        return "user/products_detail";
    }
}
