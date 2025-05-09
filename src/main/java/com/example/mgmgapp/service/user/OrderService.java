// OrderService.java - sessionIDで管理Ver：セッションIDベースの注文処理ロジックとメール送信
package com.example.mgmgapp.service.user;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.mgmgapp.entity.CartItem;
import com.example.mgmgapp.entity.OrderItems;
import com.example.mgmgapp.entity.Orders;
import com.example.mgmgapp.form.OrderForm;
import com.example.mgmgapp.repository.CartItemRepository;
import com.example.mgmgapp.repository.OrderItemRepository;
import com.example.mgmgapp.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MailService mailService;

    public List<CartItem> getCartItems(String sessionId) {
        return cartItemRepository.findBySessionId(sessionId);
    }

    public BigDecimal calculateTotal(String sessionId) {
        return getCartItems(sessionId).stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transactional
    public Orders processOrder(OrderForm form, String sessionId) {
        Orders order = new Orders();
        order.setEmail(form.getEmail());
        order.setFirstName(form.getFirstName());
        order.setLastName(form.getLastName());
        order.setPostcode(form.getPostcode());
        order.setPrefecture(form.getPrefecture());
        order.setCity(form.getCity());
        order.setAddressLine(form.getAddressLine());
        order.setBuilding(form.getBuilding());
        order.setTel(form.getTel());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(true);

        List<CartItem> cartItems = getCartItems(sessionId);
        BigDecimal total = calculateTotal(sessionId);
        order.setTotalPrice(total);

        Orders savedOrder = orderRepository.save(order);

        for (CartItem item : cartItems) {
            OrderItems orderItem = new OrderItems();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getProduct().getPrice());
            orderItemRepository.save(orderItem);
        }

        cartItemRepository.deleteBySessionId(sessionId);
        mailService.sendOrderMail(form, cartItems, total);

        return savedOrder;
    }
}
