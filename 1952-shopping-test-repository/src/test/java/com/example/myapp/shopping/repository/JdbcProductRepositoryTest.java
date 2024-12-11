package com.example.myapp.shopping.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.example.myapp.shopping.entity.Product;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyProcessorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

@JdbcTest
@Sql("JdbcProductRepositoryTest.sql")
public class JdbcProductRepositoryTest {
    
    @Autowired
    public JdbcTemplate jdbcTemplate;
    
    public JdbcProductRepository repository; 
    
    @BeforeEach
    void setUp() {
        repository = new JdbcProductRepository(jdbcTemplate);
    }

    @Test
    public void testSelectAll() {
        List<Product> products = repository.selectAll();
        products.forEach(product -> {System.out.println(product.getName());});
        assertThat(products.size(), is(5));
    }
    
    @Test
    public void testSelectById() {
        Product p02 = repository.selectById("p02");
        assertThat(p02.getId(), is("p02"));
        assertThat(p02.getName(), is("ノート"));
        assertThat(p02.getStock(), is(20));
        assertThat(p02.getPrice(), is(200));
    }
    
    @Test
    public void update() {
        Product product1 = new Product();
        product1.setId("p02");
        product1.setStock(77);
        product1.setPrice(777);
        product1.setName("ノート");
        repository.update(product1);
        Product p02 = repository.selectById("p02");
        assertThat(p02.getId(), is("p02"));
        assertThat(p02.getName(), is("ノート"));
        assertThat(p02.getStock(), is(77));
        assertThat(p02.getPrice(), is(777));
    }
    
}
