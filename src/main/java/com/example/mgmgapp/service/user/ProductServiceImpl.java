package com.example.mgmgapp.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mgmgapp.entity.Product;
import com.example.mgmgapp.repository.user.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	//DI
	private final ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
	
	@Override
	public List<Product> findAllSorted(String sortBy) {
	    return productRepository.selectAllSorted(sortBy);
	}

	@Override
	public Product findByIdProduct(Integer id) {
		return productRepository.selectById(id);
	}

}
