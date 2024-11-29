package com.example.myapp.shopping.controller;

import java.io.Serializable;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.myapp.shopping.input.CartInput;
import com.example.myapp.shopping.input.OrderInput;

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@SuppressWarnings("serial")
public class OrderSession implements Serializable {
    private CartInput cartInput;
    private OrderInput orderInput;

    public void clearData() {
        cartInput = null;
        orderInput = null;
    }

    public CartInput getCartInput() {
        return cartInput;
    }

    public void setCartInput(CartInput cartInput) {
        this.cartInput = cartInput;
    }

    public OrderInput getOrderInput() {
        return orderInput;
    }

    public void setOrderInput(OrderInput orderInput) {
        this.orderInput = orderInput;
    }
}
