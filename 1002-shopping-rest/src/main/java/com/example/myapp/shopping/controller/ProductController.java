package com.example.myapp.shopping.controller;


import com.example.myapp.shopping.entity.Product;
import com.example.myapp.shopping.service.ProductMaintenanceService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductMaintenanceService productMaintenanceService;
    public ProductController(ProductMaintenanceService productMaintenanceService) {
        this.productMaintenanceService = productMaintenanceService;
    }
        
    @GetMapping
    public ResponseEntity<List<Product>> products() {
        List<Product> all = productMaintenanceService.findAll();
        return ResponseEntity.ok().body(all);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> productById(@PathVariable String id) {
        Product p = productMaintenanceService.findById(id);
        return ResponseEntity.ok().eTag("tagtag").body(p);
    }
}
