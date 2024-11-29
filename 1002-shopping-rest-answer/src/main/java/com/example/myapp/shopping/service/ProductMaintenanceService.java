package com.example.myapp.shopping.service;

import java.util.List;

import com.example.myapp.shopping.entity.Product;

public interface ProductMaintenanceService {
    List<Product> findAll();

    Product findById(String id);
}
