package com.example.mgmgapp.controller.user;

import java.math.BigDecimal;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mgmgapp.entity.CartItems;
import com.example.mgmgapp.service.user.CartService;

@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("cart")
public String cart(Model model, HttpSession session) {
		//セッションIDを取得
		String sessionId = session.getId();
		
		//DBからカートアイテム一覧を取得
		List<CartItems> cartItems = cartService.getCartItems(sessionId);
		model.addAttribute("cartItems", cartItems);
		
		//合計金額を計算
		BigDecimal total = cartService.calculateTotal(sessionId); //calculateTotalメソッドで計算
		model.addAttribute("total", total); //合計金額
		
		return "cart";
	}
	
	//削除用コントローラ
	@PostMapping("/cart/remove/{id}")
	public String removeItemFromCart(@PathVariable("id") int id){
		//カートから指定された商品を削除（サービスを動かす）
		cartService.removeItem(id);
		return "redirect:/cart";
	}
	
	//数量変更用コントローラ(おそらく+、-ボタンで数量を変更して、確定ボタンを押したとき）
	@PostMapping("/cart/update/{id}")
	public String updateItemQuantity(
		@PathVariable("id") int id, int delta //フォームから「+1」or「-1」が来る想定
		) {
		cartService.updateQuantity(id,delta); //idと変更数（+-1）を送る
		return "redirect:/cart"; //画面を更新
	}
	
}







/*
 * もし動かなければ、↓のコードで試してください
 		
 		↑はdeltaをURLのパスから取得。
		↓はフォームのリクエストで受け取る
 */





//package com.example.mgmgapp.controller.user;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import jakarta.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.example.mgmgapp.entity.CartItems;
//import com.example.mgmgapp.service.user.CartService;
//
//@Controller
//public class CartController {
//	
//	@Autowired
//	private CartService cartService;
//	
//	@GetMapping("cart")
//	public String cart(Model model, HttpSession session) {
//		//セッションIDを取得
//		String sessionId = session.getId();
//		
//		//DBからカートアイテム一覧を取得
//		List<CartItems> cartItems = cartService.getCartItems(sessionId);
//		model.addAttribute("cartItems", cartItems);
//		
//		//合計金額を計算
//		BigDecimal total = cartService.calculateTotal(sessionId); //calculateTotalメソッドで計算
//		model.addAttribute("total", total); //合計金額
//		
//		return "cart";
//	}
//	
//	//削除用コントローラ
//	@PostMapping("/cart/remove/{id}")
//	public String removeItemFromCart(@PathVariable("id") int id){
//		//カートから指定された商品を削除（サービスを動かす）
//		cartService.removeItem(id);
//		return "redirect:/cart";
//	}
//	
//	//数量変更用コントローラ(おそらく+、-ボタンで数量を変更して、確定ボタンを押したとき）
//	@PostMapping("/cart/update/{id}")
//	public String updateItemQuantity(
//		@PathVariable("id") int id, 
//		@RequestParam("delta") int delta // フォームから送られてきたdeltaを取得
//		) {
//		cartService.updateQuantity(id, delta); //idと変更数（+-1）を送る
//		return "redirect:/cart"; //画面を更新
//	}
//	
//}









