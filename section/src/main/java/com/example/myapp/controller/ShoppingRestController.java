package com.example.myapp.controller;

import com.example.myapp.service.ShoppingService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingRestController {

    public static class Product {

        public String id;
        public String name;
        public Long price;
        public Long stock;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getPrice() {
            return price;
        }

        public void setPrice(Long price) {
            this.price = price;
        }

        public Long getStock() {
            return stock;
        }

        public void setStock(Long stock) {
            this.stock = stock;
        }
    }

    private final ShoppingService shoppingService;

    public ShoppingRestController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getShopping() {
        List<Product> allProducts = shoppingService.getAllProducts();
        return ResponseEntity.ok().body(allProducts);
    }

}
