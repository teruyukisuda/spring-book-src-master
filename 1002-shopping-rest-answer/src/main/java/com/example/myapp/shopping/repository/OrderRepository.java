package com.example.myapp.shopping.repository;

import java.util.List;

import com.example.myapp.shopping.entity.Order;

public interface OrderRepository {
    void insert(Order order);
    List<Order> selectAll();
    Order selectById(String id);
}
