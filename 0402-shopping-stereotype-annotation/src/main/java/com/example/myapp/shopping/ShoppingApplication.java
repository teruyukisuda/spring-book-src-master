package com.example.myapp.shopping;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.shopping.input.CartInput;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.example.myapp.shopping.entity.Order;
import com.example.myapp.shopping.enumeration.PaymentMethod;
import com.example.myapp.shopping.input.CartItemInput;
import com.example.myapp.shopping.input.OrderInput;
import com.example.myapp.shopping.service.OrderService;

@Configuration
public class ShoppingApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ShoppingApplication.class);
        OrderService orderService = context.getBean(OrderService.class);

        OrderInput orderInput = new OrderInput();
        orderInput.setName("東京太郎");
        orderInput.setAddress("東京都");
        orderInput.setPhone("090-0000-0000");
        orderInput.setEmailAddress("taro@example.com");
        orderInput.setPaymentMethod(PaymentMethod.CONVENIENCE_STORE);

        List<CartItemInput> cartItemInputs = new ArrayList<>();

        CartItemInput keshigom = new CartItemInput();
        keshigom.setProductId("p01");
        keshigom.setProductName("消しゴム");
        keshigom.setProductPrice(100);
        keshigom.setQuantity(3);
        cartItemInputs.add(keshigom);

        CartItemInput note = new CartItemInput();
        note.setProductId("p02");
        note.setProductName("ノート");
        note.setProductPrice(200);
        note.setQuantity(4);
        cartItemInputs.add(note);

        CartInput cartInput = new CartInput();
        cartInput.setCartItemInputs(cartItemInputs);

        Order order = orderService.placeOrder(orderInput, cartInput);

        System.out.println("注文確定処理が完了しました。注文ID=" + order.getId());
    }
}
