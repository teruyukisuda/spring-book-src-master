package com.example.myapp.shopping;

import com.example.myapp.shopping.config.ShoppingConfig;
import com.example.myapp.shopping.entity.Product;
import com.example.myapp.shopping.input.ProductMaintenanceInput;
import java.net.URI;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
//@Import(ShoppingConfig.class)
public class RestClientTest {
    
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testRegister() {
        ProductMaintenanceInput input = new ProductMaintenanceInput();
        input.setId(UUID.randomUUID().toString());
        input.setName("test2");
        input.setPrice(77);
        input.setStock(7);
        URI uri = restTemplate.postForLocation(
            "/api/products", input);

        ResponseEntity<Product> p = restTemplate.getForEntity(uri, Product.class);
        System.out.println(p.getStatusCode());
        System.out.println(p.getBody().getName());

    }
    
    
    @Test
    public void testPut() {
        ProductMaintenanceInput input = new ProductMaintenanceInput();
        input.setName("test77");
        input.setPrice(99);
        input.setStock(900);
        try {
            restTemplate.put("/api/products/{id}", input, "53aa0abb-b9c4-4c68-8ea1-f7993398ca56");
        } catch (RestClientException e) {
            throw new RuntimeException();
        }
        
    }
}
