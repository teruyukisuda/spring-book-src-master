package com.example.myapp.repository;

import com.example.myapp.controller.ShoppingRestController.Product;
import java.util.List;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingRepositoryImpl implements ShoppingRepository {
    
    private final JdbcTemplate jdbcTemplate;

    public ShoppingRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> selectAllProducts() {
        List<Product> query = jdbcTemplate.query("select * from t_product",
            new DataClassRowMapper<>(Product.class));
        return query;
    }
}
