package com.example.mgmgapp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminOderController {

    @GetMapping("/admin/orders")
    public String showOrders() {
        return "admin/orders";
    }

    @GetMapping("/admin/order/{id}")
    public String showOrderDetail(@PathVariable Integer id) {
        return "admin/order_detail";
    }
    
}
