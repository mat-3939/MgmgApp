package com.example.mgmgapp.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mgmgapp.entity.Products;

public interface CategoryRepository extends JpaRepository<Products, Integer>{

}
