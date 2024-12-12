package com.example.myapp.common.util;

import com.example.myapp.repository.entity.ProductEntity;
import com.example.myapp.view.ProductDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto entityToDto(ProductEntity product);
    List<ProductDto> entityToDto(List<ProductEntity> products);
}
