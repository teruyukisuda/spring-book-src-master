package com.example.myapp.shopping.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.example.myapp.shopping.entity.Product;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;

import com.example.myapp.shopping.input.ProductMaintenanceInput;
import com.example.myapp.shopping.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductMaintenanceServiceImplTest {
    @InjectMocks
    ProductMaintenanceServiceImpl productMaintenanceService;

    @Mock
    ProductRepository productRepository;

    @Test
    public void test_update() {
        doReturn(true).when(productRepository).update(any());
        
        ProductMaintenanceInput productMaintenanceInput = new ProductMaintenanceInput();
        productMaintenanceInput.setId("p01");
        productMaintenanceInput.setName("pname01");
        productMaintenanceInput.setPrice(100);
        productMaintenanceInput.setStock(10);
        
        productMaintenanceService.update(productMaintenanceInput);
    }

    @Test
    public void test_update_更新に失敗() {
        doReturn(false).when(productRepository).update(any());
        ProductMaintenanceInput productMaintenanceInput = new ProductMaintenanceInput();

        assertThatThrownBy(() -> {
            productMaintenanceService.update(productMaintenanceInput);
        }).isInstanceOf(OptimisticLockingFailureException.class);
    }
    
    @Test
    public void test_findAll() {
        Product p1 = new Product();
        p1.setId("p01");
        p1.setName("pname01");
        p1.setPrice(100);
        p1.setStock(1);
        Product p2 = new Product();
        p1.setId("p01");
        p1.setName("pname01");
        p1.setPrice(100);
        p1.setStock(1);
        doReturn(Arrays.asList(p1,p2)).when(productRepository).selectAll();
        
        List<Product> all = productMaintenanceService.findAll();
        assertThat(all).hasSize(2);
    }
    
    @Test
    public void test_findById() {
        Product p1 = new Product();
        p1.setId("p01");
        p1.setName("pname01");
        p1.setPrice(100);
        p1.setStock(1);
        doReturn(p1).when(productRepository).selectById("p01");

        Product byId = productMaintenanceService.findById("p01");
        assertThat(byId).isEqualTo(p1);
        assertThat(byId.getName()).isEqualTo("pname01");
    }
    
}
