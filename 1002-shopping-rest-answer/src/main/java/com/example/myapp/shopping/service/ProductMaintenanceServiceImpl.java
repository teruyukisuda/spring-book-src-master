package com.example.myapp.shopping.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.shopping.entity.Product;
import com.example.myapp.shopping.repository.ProductRepository;

@Service
@Transactional
public class ProductMaintenanceServiceImpl implements ProductMaintenanceService {
    private final ProductRepository productRepository;

    public ProductMaintenanceServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.selectAll();
    }

    @Override
    public Product findById(String id) {
        return productRepository.selectById(id);
    }
}
