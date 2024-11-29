package com.example.myapp.shopping.service;

import java.util.List;

import com.example.myapp.shopping.entity.Order;
import com.example.myapp.shopping.input.CartInput;
import com.example.myapp.shopping.input.CartItemInput;
import com.example.myapp.shopping.input.OrderInput;

public interface OrderService {
    int calculateTotalAmount(List<CartItemInput> cartItems);

    int calculateTax(int price);

    Order placeOrder(OrderInput orderInput, CartInput cartInput);
}
