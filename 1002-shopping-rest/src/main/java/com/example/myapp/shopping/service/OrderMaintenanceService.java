package com.example.myapp.shopping.service;

import java.util.List;

import com.example.myapp.shopping.entity.Order;

public interface OrderMaintenanceService {
    List<Order> findAll();

    Order findById(String id);
}
