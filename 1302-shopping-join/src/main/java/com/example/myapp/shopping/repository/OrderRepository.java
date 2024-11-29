package com.example.myapp.shopping.repository;

import com.example.myapp.shopping.entity.Order;

public interface OrderRepository {
    Order selectById(String id);
}
