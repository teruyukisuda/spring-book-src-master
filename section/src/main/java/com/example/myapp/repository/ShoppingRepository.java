package com.example.myapp.repository;

import com.example.myapp.controller.ShoppingRestController.Product;
import java.util.List;

public interface ShoppingRepository {
    public List<Product> selectAllProducts();
}
