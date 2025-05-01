package com.example.mgmgapp.service.user;



import com.example.mgmgapp.entity.CartItem;
import com.example.mgmgapp.entity.Order;
import com.example.mgmgapp.entity.OrderItem;
import com.example.mgmgapp.entity.Product;
import com.example.mgmgapp.form.OrderForm;
import com.example.mgmgapp.repository.CartItemRepository;
import com.example.mgmgapp.repository.OrderItemRepository;
import com.example.mgmgapp.repository.OrderRepository;
import com.example.mgmgapp.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public class OrderService {
    public List<CartItem> getCartItems(String sessionId) {
    	//多分こんなのが入るはず
    	/* return cartItemRepository.findBySessionId(sessionId);*/ }

    public BigDecimal calculateTotal(String sessionId) {
    	//多分こんなのが入る
//        List<CartItem> cartItems = getCartItems(sessionId);
//        BigDecimal total = BigDecimal.ZERO;
//1人しか使わない前提ならString sessionId = "demo-user"; // 固定IDとして運用をする

//        for (CartItem item : cartItems) {
//            BigDecimal price = item.getProduct().getPrice();
//            BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
//            total = total.add(price.multiply(quantity));
        }

        return total;
    }


    @Transactional
    public Order processOrder(OrderForm form, String sessionId) {
    	 Order order = new Order();
    	// Orders エンティティ生成＆保存
        // OrderItem をループで保存
        // カートのクリア
        // MailService で確認メール送信 ← ここも含まれてます！
        return order;
    }
}
