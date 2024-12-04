package com.example.myapp.shopping.controller;

import com.example.myapp.shopping.exception.DataNotFoundException;
import com.example.myapp.shopping.input.ProductMaintenanceInput;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.shopping.entity.Product;
import com.example.myapp.shopping.service.ProductMaintenanceService;

@RestController
@RequestMapping("/api/products")
public class ProductMaintenanceRestController {
    private final ProductMaintenanceService productMaintenanceService;

    public ProductMaintenanceRestController(ProductMaintenanceService productMaintenanceService) {
        this.productMaintenanceService = productMaintenanceService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productMaintenanceService.findAll();
    }
    
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        Product p = productMaintenanceService.findById(id);
        if (p == null) {
            throw new DataNotFoundException("Product with id " + id + " not found");
        }
        return p;
    }
    
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MyErrorData handleNotFound(DataNotFoundException ex) {
        MyErrorData myErrorData = new MyErrorData(404, ex.getMessage());
        return myErrorData;
    }
    
    @PostMapping
    public void addProduct(@RequestBody Product product) {
        System.out.println("post");
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable String id, @Validated @RequestBody ProductMaintenanceInput product) {
        product.setId(id);
        productMaintenanceService.update(product);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String id) {
        productMaintenanceService.delete(id);
    }
    
}
