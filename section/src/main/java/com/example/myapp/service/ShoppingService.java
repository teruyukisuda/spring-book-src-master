package com.example.myapp.service;

import com.example.myapp.controller.ShoppingRestController.Product;
import java.util.List;
import org.springframework.stereotype.Service;

public interface ShoppingService {
    public List<Product> getAllProducts();
}
