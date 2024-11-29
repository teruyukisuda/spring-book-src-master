package com.example.myapp.shopping.service;

import java.util.List;

import com.example.myapp.shopping.entity.Product;
import com.example.myapp.shopping.input.ProductMaintenanceInput;

public interface ProductMaintenanceService {
    List<Product> findAll();

    Product findById(String id);

    void update(ProductMaintenanceInput productMaintenanceInput);

    void delete(String id);
}
