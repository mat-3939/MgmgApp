package com.example.mgmgapp.service.admin;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mgmgapp.entity.Orders;
import com.example.mgmgapp.repository.admin.AdminOrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminOrderService {
    /*DI*/
    private final AdminOrderRepository adminOrderRepository;

    /*注文一覧取得*/
    public List<Orders> findAllOrders() {
        return adminOrderRepository.findAll();
    }

    /*注文詳細取得*/
    public Orders findOrderById(Integer id) {
        return adminOrderRepository.findById(id).orElse(null);
    }

    /*注文ステータス更新*/
    public void updateOrderStatus(Integer id, Boolean status) {
        Orders order = adminOrderRepository.findById(id).orElse(null);
        order.setStatus(status);
        adminOrderRepository.save(order);
    }

    /*検索条件に基づいて注文を検索*/
    public List<Orders> searchOrders(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return findAllOrders();
        }
        
        // 複数の条件で検索（姓、名、メールアドレス、電話番号など）
        List<Orders> byLastName = adminOrderRepository.findByLastNameContainingOrderByOrderDateDesc(keyword);
        List<Orders> byFirstName = adminOrderRepository.findByFirstNameContainingOrderByOrderDateDesc(keyword);
        List<Orders> byEmail = adminOrderRepository.findByEmailContainingOrderByOrderDateDesc(keyword);
        List<Orders> byTel = adminOrderRepository.findByTelContainingOrderByOrderDateDesc(keyword);
        
        // 結果をマージして重複を排除
        Set<Orders> uniqueOrders = new HashSet<>();
        uniqueOrders.addAll(byLastName);
        uniqueOrders.addAll(byFirstName);
        uniqueOrders.addAll(byEmail);
        uniqueOrders.addAll(byTel);
        
        // リストに変換して日付順にソート
        List<Orders> result = new ArrayList<>(uniqueOrders);
        result.sort(Comparator.comparing(Orders::getOrderDate).reversed());
        
        return result;
    }
    
    /*詳細検索*/
    public List<Orders> advancedSearch(String lastName, String firstName, 
                                      LocalDateTime startDate, LocalDateTime endDate,
                                      BigDecimal minAmount, Boolean status) {
        // 複雑な検索条件の場合は、カスタムリポジトリメソッドを作成するか
        // 複数の条件を組み合わせて検索する
        
        // 例: 日付範囲と金額で検索
        if (startDate != null && endDate != null && minAmount != null) {
            return adminOrderRepository.findByOrderDateBetweenAndTotalPriceGreaterThanOrderByOrderDateDesc(
                startDate, endDate, minAmount);
        }
        
        // 他の条件の組み合わせ...
        
        return findAllOrders();
    }
}
