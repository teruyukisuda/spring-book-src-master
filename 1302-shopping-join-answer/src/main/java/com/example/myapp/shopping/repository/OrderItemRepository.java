package com.example.myapp.shopping.repository;

import com.example.myapp.shopping.entity.OrderItem;

public interface OrderItemRepository {
    OrderItem selectById(String id);
}
