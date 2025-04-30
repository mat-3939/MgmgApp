package com.example.mgmgapp.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mgmgapp.entity.Product;
import com.example.mgmgapp.repository.user.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllSorted(String sortBy) {
        return productRepository.findAll();
    }

    @Override
    public Product findByIdProduct(Integer id) {
        return productRepository.findById(id).orElse(null);
    }
}
