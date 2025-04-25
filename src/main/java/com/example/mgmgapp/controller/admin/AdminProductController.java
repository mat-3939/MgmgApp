package com.example.mgmgapp.controller.admin;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.mgmgapp.entity.Categories;
import com.example.mgmgapp.entity.Product;
import com.example.mgmgapp.form.ProductForm;
import com.example.mgmgapp.service.admin.AdminCategoryService;
import com.example.mgmgapp.service.admin.AdminProductService;

/**
 * 管理者用の商品操作コントローラ
 * 商品の一覧表示、登録、更新、削除などを処理する
 */
@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

	private final AdminProductService adminProductService;
	private final AdminCategoryService adminCategoryService;

	@Autowired
	public AdminProductController(AdminProductService adminProductService,
	                              AdminCategoryService adminCategoryService) {
		this.adminProductService = adminProductService;
		this.adminCategoryService = adminCategoryService;
	}

	/**
	 * 商品一覧を表示（デフォルトは新着順）
	 */
	@GetMapping
	public String listProducts(
			@RequestParam(required = false) String keyword,
			@RequestParam(required = false) BigDecimal minPrice,
			@RequestParam(required = false) BigDecimal maxPrice,
			@RequestParam(required = false, defaultValue = "new") String sort,
			Model model) {

		List<Product> products;

		if (keyword != null && !keyword.isEmpty()) {
			products = adminProductService.searchByKeyword(keyword);
		} else if (minPrice != null && maxPrice != null) {
			products = adminProductService.searchByPriceRange(minPrice, maxPrice);
		} else {
			products = adminProductService.getSortedProducts(sort);
		}

		model.addAttribute("products", products);
		return "admin/products";
	}

	/**
	 * 商品新規登録画面を表示
	 */
	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("productForm", new ProductForm());
		model.addAttribute("categories", adminCategoryService.findAll());
		return "admin/product_form";
	}

	/**
	 * 商品の新規登録を実行
	 */
	@PostMapping("/new")
	public String createProduct(@Valid @ModelAttribute ProductForm productForm,
	                            BindingResult result,
	                            @RequestParam("imageFile") MultipartFile imageFile,
	                            Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("categories", adminCategoryService.findAll());
			return "admin/product_form";
		}

		// フォーム → エンティティ変換 & 画像保存
		Product product = convertToEntity(productForm, imageFile);

		adminProductService.createProduct(product);

		return "redirect:/admin/products";
	}

	/**
	 * 商品編集画面を表示
	 */
	@GetMapping("/{id}/edit")
	public String showEditForm(@PathVariable Integer id, Model model) {
		Product product = adminProductService.getProductById(id).orElseThrow();
		ProductForm form = convertToForm(product);

		model.addAttribute("productForm", form);
		model.addAttribute("categories", adminCategoryService.findAll());

		return "admin/product_form";
	}

	/**
	 * 商品の更新を実行
	 */
	@PostMapping("/{id}/edit")
	public String updateProduct(@PathVariable Integer id,
	                            @Valid @ModelAttribute ProductForm productForm,
	                            BindingResult result,
	                            @RequestParam("imageFile") MultipartFile imageFile,
	                            Model model) {
		if (result.hasErrors()) {
			model.addAttribute("categories", adminCategoryService.findAll());
			return "admin/product_form";
		}

		Product existingProduct = adminProductService.getProductById(id).orElseThrow();

		// カテゴリ取得
		Categories category = adminCategoryService.getCategoryById(productForm.getCategoryId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

		// 画像がアップロードされている場合は保存処理
		if (!imageFile.isEmpty()) {
			String imagePath = adminProductService.saveImage(imageFile, category);
			existingProduct.setImagePath(imagePath);
		}

		// 残りの項目を更新
		existingProduct.setName(productForm.getName());
		existingProduct.setDescription(productForm.getDescription());
		existingProduct.setPrice(productForm.getPrice());
		existingProduct.setStock(productForm.getStock());
		existingProduct.setCategory(category);

		adminProductService.updateProduct(existingProduct);

		return "redirect:/admin/products";
	}

	/**
	 * 商品の削除
	 */
	@PostMapping("/{id}/delete")
	public String deleteProduct(@PathVariable Integer id) {
		adminProductService.deleteProduct(id);
		return "redirect:/admin/products";
	}

	/**
	 * ProductForm → Product への変換
	 */
	public Product convertToEntity(ProductForm form, MultipartFile imageFile) {
		Product product = new Product();
		product.setName(form.getName());
		product.setDescription(form.getDescription());
		product.setPrice(form.getPrice());
		product.setStock(form.getStock());

		Categories category = adminCategoryService.getCategoryById(form.getCategoryId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
		product.setCategory(category);

		if (imageFile != null && !imageFile.isEmpty()) {
			String imagePath = adminProductService.saveImage(imageFile, category);
			product.setImagePath(imagePath);
		}

		return product;
	}

	/**
	 * Product → ProductForm への変換（編集画面で初期値表示）
	 */
	public ProductForm convertToForm(Product product) {
		ProductForm form = new ProductForm();
		form.setName(product.getName());
		form.setDescription(product.getDescription());
		form.setPrice(product.getPrice());
		form.setStock(product.getStock());
		form.setCategoryId(product.getCategory().getId());

		return form;
	}

}
