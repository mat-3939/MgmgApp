package com.example.mgmgapp.controller.user;

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

		 // テスト用に仮のセッションIDをセット
	    String sessionId = "sessionId1";  // ← 仮のセッションID
		//	    // セッションIDを取得
//	    String sessionId = session.getId();

	    // DBからカートアイテム一覧を取得
	    List<CartItems> cartItems = cartService.getCartItems(sessionId);
	    model.addAttribute("cartItems", cartItems);

	    //★★★★★
	    // 合計金額を計算
	    
	    int total = 0;
	    System.out.println("cartItems is null? " + (cartItems == null));
	    System.out.println("cartItems size: " + (cartItems != null ? cartItems.size() : "null"));

//	    for (CartItems item : cartItems) {
//	        int productId = item.getProduct().getId(); // product_id を取得
//	        int price = cartService.getProductPrice(productId); // 単価を取得
//	        total += price * item.getQuantity(); // 小計を足す
//	        
//	        System.out.println("〇〇〇");
//	        System.out.println(item.getProduct());
//	        System.out.println("〇〇〇");
//	    }

	    
	    System.out.println("★★★");
	    System.out.println(total);
	    System.out.println("★★★");
//
//
	    model.addAttribute("total", total);

	    return "user/cart";
	}
	
	//削除用コントローラ
	@PostMapping("cart/remove/{id}")
	public String removeItemFromCart(@PathVariable("id") int id){
		//カートから指定された商品を削除（サービスを動かす）
		cartService.removeItem(id);
		return "redirect:/cart";
	}
	
	//数量変更用コントローラ(おそらく+、-ボタンで数量を変更して、確定ボタンを押したとき）
	@PostMapping("cart/update/{id}")
	public String updateItemQuantity(
		@PathVariable("id") int id, int delta //フォームから「+1」or「-1」が来る想定
		) {
		cartService.updateQuantity(id,delta); //idと変更数（+-1）を送る
		return "redirect:/cart"; //画面を更新
	}
	
}






