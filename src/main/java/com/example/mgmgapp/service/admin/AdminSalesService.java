package com.example.mgmgapp.service.admin;

import org.springframework.stereotype.Service;

import com.example.mgmgapp.repository.admin.AdminOrderItemRepository;
import com.example.mgmgapp.repository.admin.AdminOrderRepository;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminSalesService {
    /*DI*/
    private final AdminOrderRepository adminOrderRepository;
    private final AdminOrderItemRepository adminOrderItemRepository;
    
    /*注文数の合計を取得*/
    public int getOrderCount() {
        return adminOrderItemRepository.findAll().size();
    }

    /*注文数の対応済みの合計を取得*/
    public int getCompletedOrderCount() {
        return (int) adminOrderRepository.findAll().stream()
            .filter(order -> order.getStatus() == true)
            .count();
    }

    /*注文数の未対応の合計を取得*/
    public int getPendingOrderCount() {
        return (int) adminOrderRepository.findAll().stream()
            .filter(order -> order.getStatus() == false)
            .count();
    }

    /*本日の売上金額を取得*/
    public int getTodaySalesAmount() {
        return adminOrderItemRepository.findAll().stream()
            .filter(orderItem -> orderItem.getOrder().getOrderDate().toLocalDate().equals(LocalDate.now()))
            .mapToInt(orderItem -> orderItem.getProduct().getPrice())
            .sum();
    }

    
    
}
