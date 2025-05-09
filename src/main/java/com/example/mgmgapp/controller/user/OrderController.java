
//OrderController.java - 注文処理コントローラ
package com.example.mgmgapp.controller.user;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mgmgapp.form.OrderForm;
import com.example.mgmgapp.service.user.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

 private final OrderService orderService;

 @GetMapping("/form")
 public String showOrderForm(Model model, HttpSession session) {
     model.addAttribute("orderForm", new OrderForm());
     model.addAttribute("cartItems", orderService.getCartItems());
     model.addAttribute("totalPrice", orderService.calculateTotal());
     return "user/order_form";
 }

 @PostMapping("/confirm")
 public String confirmOrder(@Valid @ModelAttribute("orderForm") OrderForm form,
                             BindingResult bindingResult,
                             Model model) {
     if (bindingResult.hasErrors()) {
         model.addAttribute("cartItems", orderService.getCartItems());
         model.addAttribute("totalPrice", orderService.calculateTotal());
         return "user/order_form";
     }
     model.addAttribute("orderForm", form);
     model.addAttribute("cartItems", orderService.getCartItems());
     model.addAttribute("totalPrice", orderService.calculateTotal());
     return "user/order_confirm";
 }

 @PostMapping("/complete")
 public String completeOrder(@ModelAttribute("orderForm") OrderForm form,
                              Model model) {
     var order = orderService.processOrder(form);
     model.addAttribute("order", order);
     return "user/order_complete";
 }
}
