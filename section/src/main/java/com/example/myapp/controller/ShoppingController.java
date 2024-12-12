package com.example.myapp.controller;

import com.example.myapp.service.ShoppingService;
import com.example.myapp.view.ProductDto;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shoppings")
public class ShoppingController {


    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping
    public String getShopping(Model model) {
        List<ProductDto> allProducts = shoppingService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "products";
            
    }
    
    @GetMapping("/showall")
    public String showAllUser(Model model) {
        model.addAttribute("showall", "誰でも見られるページ");
        return "showall";
    }

}
