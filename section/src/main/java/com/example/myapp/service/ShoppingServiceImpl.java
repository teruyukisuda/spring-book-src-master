package com.example.myapp.service;

import com.example.myapp.controller.ShoppingRestController.Product;
import com.example.myapp.repository.ShoppingRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShoppingServiceImpl implements ShoppingService {
    private final ShoppingRepository shoppingRepository;
    public ShoppingServiceImpl(ShoppingRepository shoppingRepository) {
        this.shoppingRepository = shoppingRepository;
    }
    @Override
    public List<Product> getAllProducts() {
        return shoppingRepository.selectAllProducts();
    }
}
