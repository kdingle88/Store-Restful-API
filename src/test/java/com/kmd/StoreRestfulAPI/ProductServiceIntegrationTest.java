package com.kmd.StoreRestfulAPI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductServiceIntegrationTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @BeforeEach
    public void before() {
        productRepository.save(new Product(1L,"Ice Cream"));
        productRepository.save(new Product(2L,"Brownie"));
        productRepository.save(new Product(3L,"Cake"));
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();

        assertEquals(3,products.size());

    }

    @Test
    public void testGetAllProductsByType() {
        List<Product> cakeProducts = productService.getProductsByType("Cake");

        assertEquals(1,cakeProducts.size());
    }


}
