package com.example.myapp.repository;

import com.example.myapp.repository.entity.ProductEntity;
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
    public List<ProductEntity> selectAllProducts() {
        List<ProductEntity> products = jdbcTemplate.query("select * from t_product",
            new DataClassRowMapper<>(ProductEntity.class));
        return products;
    }
}
