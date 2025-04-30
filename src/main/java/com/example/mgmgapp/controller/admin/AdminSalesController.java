package com.example.mgmgapp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mgmgapp.service.admin.AdminSalesService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminSalesController {
    /*DI*/
    private final AdminSalesService adminSalesService;

    /*ホーム*/
    @GetMapping("/admin")
    public String showAdminHome(Model model) {
        /*注文数の合計*/
        model.addAttribute("orderCount", adminSalesService.getOrderCount());
        /*注文数の対応済みの合計*/
        model.addAttribute("completedOrderCount", adminSalesService.getCompletedOrderCount());
        /*注文数の未対応の合計*/
        model.addAttribute("pendingOrderCount", adminSalesService.getPendingOrderCount());
        
        /*本日の売上金額*/
        model.addAttribute("todaySalesAmount", adminSalesService.getTodaySalesAmount());
        /*週間の売上金額*/
        model.addAttribute("weeklySalesAmount", adminSalesService.getWeeklySalesAmount());
        /*月間の売上金額*/
        model.addAttribute("monthlySalesAmount", adminSalesService.getMonthlySalesAmount());
        
        return "admin/home";
    }
}
