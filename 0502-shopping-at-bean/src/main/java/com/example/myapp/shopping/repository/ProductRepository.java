package com.example.myapp.shopping.repository;

import com.example.myapp.shopping.entity.Product;

public interface ProductRepository {
    Product selectById(String id);

    boolean update(Product product);
}
