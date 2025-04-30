package com.example.mgmgapp.controller.admin;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mgmgapp.entity.Admins;
import com.example.mgmgapp.form.LoginForm;
import com.example.mgmgapp.repository.admin.AdminRepository;

@Controller
@RequestMapping("/admin/login")
public class LoginController {
@GetMapping
public String showLogin(@ModelAttribute LoginForm form) {
// templates配下のlogin.htmlへ遷移
return "admin/login";
}

@PostMapping
public String login(@ModelAttribute LoginForm form, Model model) {
    Optional<Admins> adminOpt = AdminRepository.findByUserName(form.getUserName());

    if (adminOpt.isPresent()) {
        Admins admin = adminOpt.get();

        // パスワード比較（平文の場合）
        if (admin.getPassword().equals(form.getPassword())) {
            model.addAttribute("message", "ログイン成功！");
            return "admin/home"; // ログイン成功時の画面に遷移
        }
    }

    // 認証失敗時
    model.addAttribute("error", "ユーザー名またはパスワードが正しくありません");
    return "admin/login";
}
}
