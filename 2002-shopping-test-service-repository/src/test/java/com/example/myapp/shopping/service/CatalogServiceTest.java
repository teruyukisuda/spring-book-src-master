package com.example.myapp.shopping.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;

import com.example.myapp.shopping.entity.Product;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Sql("CatalogServiceTest.sql")
public class CatalogServiceTest {
    
    @Autowired
    private CatalogService catalogService;
    
    @Test
    public void test_findAll() {
        List<Product> all = catalogService.findAll();
        assertThat(all.size(), is(5));
    }
    @Test
    public void test_findById() {
        Product p= catalogService.findById("p04");
        assertThat(p.getName(), is("pname04"));
        assertThat(p.getPrice(), is(400));
    }
}
