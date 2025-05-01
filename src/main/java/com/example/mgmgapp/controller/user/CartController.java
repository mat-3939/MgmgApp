package com.example.mgmgapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mgmgapp.service.user.CartService;

@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("cart")
public String cart() {
		return "cart";
	}
	
	//削除用コントローラ
	@PostMapping("/cart/remove/{id}")
	public String removeItemFromCart(@PathVariable("id") int id){
		//カートから指定された商品を削除（サービスを動かす）
		cartService.removeItem(id);
		return "cart";
	}
	
	//数量変更用コントローラ(おそらく+、-ボタンで数量を変更して、確定ボタンを押したとき）
	public String updateItemQuantity(@PathVariable("id") int id, int quantity) {
		//持ってきた変更後の数量を更新する
		return("");
	}
}