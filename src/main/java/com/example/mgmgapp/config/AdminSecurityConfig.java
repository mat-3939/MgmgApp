package com.example.mgmgapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AdminSecurityConfig {

    @Bean
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/login", "/css/**", "/js/**").permitAll() //ログインと静的リソースは許可
                .requestMatchers("/admin/**").authenticated() // ログイン済の場合のみアクセス可
                .anyRequest().denyAll() // 他のURLはすべて拒否（ユーザー側がない前提）
            )
            .formLogin(form -> form
                .loginPage("/admin/login")
                .loginProcessingUrl("/authentication")         //form の action と一致させる
                .usernameParameter("user_nameInput")            //フォーム側の name 属性
                .passwordParameter("passwordInput")            //フォーム側の name 属性
                .defaultSuccessUrl("/admin/", true)            //成功時 ←マージ後に成功後のトップページ「/～」を付け足す必要あり。
                .failureUrl("/admin/login?error")              //失敗時
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login?logout")
            );
        return http.build();
    }
}





