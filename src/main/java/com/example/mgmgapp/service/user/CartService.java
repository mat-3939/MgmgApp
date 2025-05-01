package com.example.mgmgapp.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		
	//カート内の商品数を変更（+、-ボタンの使用）
		public void updateQuantity(int id, int quantity) {
		    Optional<CartItem> itemOpt = cartItemRepository.findById(id);

		    if (itemOpt.isPresent()) {
		        CartItem item = itemOpt.get();
		        if (quantity <= 0) {
		            cartItemRepository.delete(item); // 数量0以下なら削除
		        } else {
		            item.setQuantity(quantity);
		            cartItemRepository.save(item); // 数量更新
		        }
		    }
		}

	
	}
}
