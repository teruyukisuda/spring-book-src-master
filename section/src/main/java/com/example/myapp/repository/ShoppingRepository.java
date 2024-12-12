package com.example.myapp.repository;

import com.example.myapp.repository.entity.ProductEntity;
import java.util.List;

public interface ShoppingRepository {
    public List<ProductEntity> selectAllProducts();
}
