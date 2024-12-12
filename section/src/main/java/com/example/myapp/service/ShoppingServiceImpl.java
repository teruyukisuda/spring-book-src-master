package com.example.myapp.service;

import com.example.myapp.common.util.ProductMapper;
import com.example.myapp.repository.ShoppingRepository;
import com.example.myapp.repository.entity.ProductEntity;
import com.example.myapp.view.ProductDto;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShoppingServiceImpl implements ShoppingService {
    private final ShoppingRepository shoppingRepository;
    private final ProductMapper productMapper;
    
    public ShoppingServiceImpl(ShoppingRepository shoppingRepository, ProductMapper productMapper) {
        this.shoppingRepository = shoppingRepository;
        this.productMapper = productMapper;
    }
    
    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductEntity> productEntities = shoppingRepository.selectAllProducts();
        return productMapper.entityToDto(productEntities);
    }
}
