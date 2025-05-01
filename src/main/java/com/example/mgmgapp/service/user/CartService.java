package com.example.mgmgapp.service.user;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mgmgapp.entity.CartItems;
import com.example.mgmgapp.repository.CartItemRepository;

@Service
public class CartService {
	
	@Autowired
	private CartItemRepository cartItemRepository;

	//カート内の商品を削除
	public void removeItem(int id) {
		//カート内の商品を削除処理
		//RepositoryないのdeleteByIdメソッドを起動
		cartItemRepository.deleteById(id);
	}
	
	//カート内商品を一覧取得（セッション単位）
	public List<CartItems> getCartItems(String sessionId){
		return cartItemRepository.findBySessionId(sessionId);
	}
	
	//合計金額の計算（小計）
	public BigDecimal calculateTotal(String sessionId) {
		return cartItemRepository.findBySessionId(sessionId)
				.stream()
				.map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
		
	//カート内の商品数を変更（+、-ボタンの使用）
		public void updateQuantity(int id, int delta) {
			CartItems item = cartItemRepository.findById(id).orElse(null);//リポジトリからidに一致する商品情報を引き出す
			if (item != null) {
		        int newQty = item.getQuantity() + delta; //新しい数を計算
		        if (newQty <= 0) {
		            cartItemRepository.deleteById(id);//もし０以下の数字になったら削除
		        } else {
		            item.setQuantity(newQty);
		            cartItemRepository.save(item);
		        }
		    }
		}
	
}
