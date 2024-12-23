package com.example.myapp.shopping.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.myapp.shopping.entity.Product;
import com.example.myapp.shopping.service.CatalogService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CatalogController.class)
public class CatalogControllerTest {
    
    @Autowired
    private MockMvc mock;
    
    @MockBean
    private CatalogService catalogService;
    
    @Test
    public void test_DisplayList() throws Exception {
        
        Product p = new Product();
        p.setName("test99");
        Product p2 = new Product();
        p2.setName("test7");
        List<Product> list = Arrays.asList(p, p2);

        doReturn(list).when(catalogService).findAll();

        mock.perform(get("/catalog/display-list"))
            .andExpect(status().isOk())
            .andExpect(view().name("catalog/productList"))
            .andExpect(content().string(containsString("test99")));
    }
    
    @Test
    public void test_DisplayDetails() throws Exception {
        Product p = new Product();
        p.setId("t01");
        p.setName("test01");
        
        doReturn(p).when(catalogService).findById("t01");
        
        mock.perform(get("/catalog/display-details").param("productId", "t01"))
            .andExpect(status().isOk())
            .andExpect(view().name("catalog/productDetails"))
            .andExpect(content().string(containsString("test01")));
        
    }
    
}
