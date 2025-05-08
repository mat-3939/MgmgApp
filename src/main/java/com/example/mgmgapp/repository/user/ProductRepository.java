package com.example.mgmgapp.repository.user;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mgmgapp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 完全一致（大文字小文字無視）
    List<Product> findByNameEqualsIgnoreCase(String name);
}
