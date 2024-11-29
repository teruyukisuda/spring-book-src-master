package com.example.myapp.shopping.service;

import com.example.myapp.shopping.entity.Order;
import com.example.myapp.shopping.input.CartInput;
import com.example.myapp.shopping.input.OrderInput;

public interface OrderService {
    Order placeOrder(OrderInput orderInput, CartInput cartInput);
}
