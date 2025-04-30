package com.example.mgmgapp.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.mgmgapp.entity.Orders;
import com.example.mgmgapp.service.admin.AdminOrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminOderController {
    /*DI*/
    private final AdminOrderService adminOrderService;

    /*注文一覧*/
    @GetMapping("/admin/orders")
    public String showOrders(Model model) {
        List<Orders> orders = adminOrderService.findAllOrders();
        model.addAttribute("orders", orders);
        return "admin/orders";
    }

    /*注文詳細*/
    @GetMapping("/admin/order/{id}")
    public String showOrderDetail(@PathVariable Integer id) {
        return "admin/order_detail";
    }
    
}
