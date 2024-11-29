package com.example.myapp.shopping.service;

import java.util.List;

import com.example.myapp.shopping.entity.Product;

public interface CatalogService {
    List<Product> findAll();

    Product findById(String id);
}
