package com.example.myapp.shopping.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.shopping.entity.Order;
import com.example.myapp.shopping.repository.OrderRepository;

@Service
@Transactional
public class OrderMaintenanceServiceImpl implements OrderMaintenanceService {

    private final OrderRepository orderRepository;

    public OrderMaintenanceServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.selectAll();
    }

    @Override
    public Order findById(String id) {
        return orderRepository.selectById(id);
    }
}
