package com.example.mgmgapp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mgmgapp.service.admin.AdminSalesService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/*ページURL(ホーム):http://localhost:8080/admin*/
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
        /*年間の売上金額*/
        model.addAttribute("yearlySalesAmount", adminSalesService.getYearlySalesAmount());

        /*週間の売上件数*/
        model.addAttribute("weeklySalesCount", adminSalesService.getWeeklySalesCount());
        /*月間の売上件数*/
        model.addAttribute("monthlySalesCount", adminSalesService.getMonthlySalesCount());
        /*年間の売上件数*/
        model.addAttribute("yearlySalesCount", adminSalesService.getYearlySalesCount());
        
        return "admin/home";
    }

    @GetMapping("/admin/sales-data")
    @ResponseBody
    public Map<String, Object> getSalesData(@RequestParam String period) {
        Map<String, Object> response = new HashMap<>();
        
        switch (period) {
            case "weekly":
                response.put("amount", adminSalesService.getWeeklySalesAmount());
                response.put("count", adminSalesService.getWeeklySalesCount());
                break;
            case "monthly":
                response.put("amount", adminSalesService.getMonthlySalesAmount());
                response.put("count", adminSalesService.getMonthlySalesCount());
                break;
            case "yearly":
                response.put("amount", adminSalesService.getYearlySalesAmount());
                response.put("count", adminSalesService.getYearlySalesCount());
                break;
        }
        
        return response;
    }
}
