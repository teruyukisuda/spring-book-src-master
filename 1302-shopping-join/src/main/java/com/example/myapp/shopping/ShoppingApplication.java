package com.example.myapp.shopping;


import com.example.myapp.shopping.entity.OrderItem;
import com.example.myapp.shopping.repository.OrderItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ShoppingApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ShoppingApplication.class, args);
        OrderItemRepository orderItemRepository = context.getBean(OrderItemRepository.class);
        OrderItem orderItem = orderItemRepository.selectById("i01");

        System.out.println("------------- OrderItemの中身 -------------");
        System.out.println(orderItem.getPriceAtOrder());
        System.out.println(orderItem.getProduct());
    }
}

