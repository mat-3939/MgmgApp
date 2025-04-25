package com.example.mgmgapp.service.admin;

import java.util.List;

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
}
