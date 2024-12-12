package com.example.myapp.controller.api;

import com.example.myapp.service.ShoppingService;
import com.example.myapp.view.ProductDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shoppings")
public class ShoppingRestController {

    private final ShoppingService shoppingService;

    public ShoppingRestController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getShopping() {
        List<ProductDto> allProducts = shoppingService.getAllProducts();
        return ResponseEntity.ok().body(allProducts);
    }

}
