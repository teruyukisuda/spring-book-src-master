package com.example.myapp.shopping.repository;

import com.example.myapp.shopping.entity.Order;

public class JdbcOrderRepository implements OrderRepository {
    @Override
    public void insert(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("引数が不正");
        }
        System.out.println("注文を登録しました。id=" + order.getId());
    }
}
