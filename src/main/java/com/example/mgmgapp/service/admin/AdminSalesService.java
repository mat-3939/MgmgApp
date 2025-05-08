package com.example.mgmgapp.service.admin;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    
    //次回ここから
    /*注文件数の合計を取得*/
    public int getOrderCount() {
        return adminOrderItemRepository.countDistinctOrderCount();
    }

    /*注文商品の合計数を取得（各注文の商品数を合計）*/
    public int getTotalItemCount() {
        return adminOrderItemRepository.findAll().size();
    }

    /*注文ごとの平均商品数を取得*/
    public double getAverageItemsPerOrder() {
        int orderCount = getOrderCount();
        if (orderCount == 0) {
            return 0;
        }
        int totalItems = getTotalItemCount();
        return (double) totalItems / orderCount;
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
    public BigDecimal getTodaySalesAmount() {
        return adminOrderItemRepository.findAll().stream()
            .filter(orderItem -> orderItem.getOrder().getOrderDate().toLocalDate().equals(LocalDate.now()))
            .map(orderItem -> orderItem.getPrice() != null ? 
                orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())) : 
                BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /*週間の売上金額を取得*/
    public BigDecimal getWeeklySalesAmount() {
        return adminOrderItemRepository.findAll().stream()
            .filter(orderItem -> orderItem.getOrder().getOrderDate().toLocalDate().isAfter(LocalDate.now().minusDays(7)))
            .map(orderItem -> orderItem.getPrice() != null ? 
                orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())) : 
                BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /*月間の売上金額を取得*/
    public BigDecimal getMonthlySalesAmount() {
        return adminOrderItemRepository.findAll().stream()
            .filter(orderItem -> orderItem.getOrder().getOrderDate().toLocalDate().isAfter(LocalDate.now().minusMonths(1)))
            .map(orderItem -> orderItem.getPrice() != null ? 
                orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())) : 
                BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /*年間の売上金額を取得*/
    public BigDecimal getYearlySalesAmount() {
        return adminOrderItemRepository.findAll().stream()
            .filter(orderItem -> orderItem.getOrder().getOrderDate().toLocalDate().isAfter(LocalDate.now().minusYears(1)))
            .map(orderItem -> orderItem.getPrice() != null ? 
                orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())) : 
                BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /*週間の売上件数を取得*/
    public int getWeeklySalesCount() {
        return (int) adminOrderItemRepository.findAll().stream()
            .filter(orderItem -> orderItem.getOrder().getOrderDate().toLocalDate().isAfter(LocalDate.now().minusDays(7)))
            .count();
    }

    /*月間の売上件数を取得*/
    public int getMonthlySalesCount() {
        return (int) adminOrderItemRepository.findAll().stream()
            .filter(orderItem -> orderItem.getOrder().getOrderDate().toLocalDate().isAfter(LocalDate.now().minusMonths(1)))
            .count();
    }

    /*年間の売上金額を取得*/
    public int getYearlySalesCount() {
        return (int) adminOrderItemRepository.findAll().stream()
            .filter(orderItem -> orderItem.getOrder().getOrderDate().toLocalDate().isAfter(LocalDate.now().minusYears(1)))
            .count();
    }    
    
}
