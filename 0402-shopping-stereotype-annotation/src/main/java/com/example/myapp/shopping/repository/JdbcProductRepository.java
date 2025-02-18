package com.example.myapp.shopping.repository;

import com.example.myapp.shopping.entity.Product;

public class JdbcProductRepository implements ProductRepository {
    @Override
    public Product selectById(String id) {
        if ("p01".equals(id)) {
            Product product = new Product();
            product.setId("p01");
            product.setStock(10);
            return product;
        }
        if ("p02".equals(id)) {
            Product product = new Product();
            product.setId("p02");
            product.setStock(20);
            return product;
        }
        throw new IllegalArgumentException("引数が不正");
    }

    @Override
    public boolean update(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("引数が不正");
        }
        System.out.println("商品を更新しました。id=" + product.getId());
        return true;
    }
}
