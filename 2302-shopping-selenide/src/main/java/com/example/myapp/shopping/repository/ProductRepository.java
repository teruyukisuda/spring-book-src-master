package com.example.myapp.shopping.repository;

import java.util.List;

import com.example.myapp.shopping.entity.Product;

public interface ProductRepository {
    Product selectById(String id);

    List<Product> selectAll();

    boolean update(Product product);
}
