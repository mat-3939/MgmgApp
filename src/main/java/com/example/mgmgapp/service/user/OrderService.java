
//(テストが終わって本番ではこちらかも)
//package com.example.mgmgapp.service.user;
//
//
//
//import com.example.mgmgapp.entity.CartItem;
//import com.example.mgmgapp.entity.Order;
//import com.example.mgmgapp.entity.OrderItem;
//import com.example.mgmgapp.entity.Product;
//import com.example.mgmgapp.form.OrderForm;
//import com.example.mgmgapp.repository.CartItemRepository;
//import com.example.mgmgapp.repository.OrderItemRepository;
//import com.example.mgmgapp.repository.OrderRepository;
//import com.example.mgmgapp.repository.ProductRepository;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//
//
//public class OrderService {
//    public List<CartItem> getCartItems(String sessionId) {
//    	//多分こんなのが入るはず
//    	/* return cartItemRepository.findBySessionId(sessionId);*/ }
//
//    public BigDecimal calculateTotal(String sessionId) {
//    	//多分こんなのが入る
////        List<CartItem> cartItems = getCartItems(sessionId);
////        BigDecimal total = BigDecimal.ZERO;
////1人しか使わない前提ならString sessionId = "demo-user"; // 固定IDとして運用をする
//
//        for (CartItem item : cartItems) {
////            BigDecimal price = item.getProduct().getPrice();
////            BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
////            total = total.add(price.multiply(quantity));
//        }
//
//        return total;
//    }
//
//
//    @Transactional
//    public Order processOrder(OrderForm form, String sessionId) {
//    	 Order order = new Order();
//    	// Orders エンティティ生成＆保存
//        // OrderItem をループで保存
//        // カートのクリア
//        // MailService で確認メール送信 ← ここも含まれてます！
//        return order;
//    }
//}

// OrderService.java - sessionId固定の注文処理サービス(1人のみ)
package com.example.mgmgapp.service.user;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.mgmgapp.entity.CartItem;
import com.example.mgmgapp.entity.Order;
import com.example.mgmgapp.entity.OrderItem;
import com.example.mgmgapp.form.OrderForm;
import com.example.mgmgapp.repository.CartItemRepository;
import com.example.mgmgapp.repository.OrderItemRepository;
import com.example.mgmgapp.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final String FIXED_SESSION_ID = "demo-user";

    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MailService mailService;

    public List<CartItem> getCartItems() {
        return cartItemRepository.findBySessionId(FIXED_SESSION_ID);
    }

    public BigDecimal calculateTotal() {
        return getCartItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transactional
    public Order processOrder(OrderForm form) {
        Order order = new Order();
        order.setEmail(form.getEmail());
        order.setFirstName(form.getFirstName());
        order.setLastName(form.getLastName());
        order.setPostcode(form.getPostcode());
        order.setAddress(form.getAddress());
        order.setTel(form.getTel());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(true);

        List<CartItem> cartItems = getCartItems();
        BigDecimal total = calculateTotal();
        order.setTotalPrice(total);

        order = orderRepository.save(order);

        for (CartItem item : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getProduct().getPrice());
            orderItemRepository.save(orderItem);
        }

        cartItemRepository.deleteBySessionId(FIXED_SESSION_ID);
        mailService.sendOrderMail(form, cartItems, total);

        return order;
    }
}
