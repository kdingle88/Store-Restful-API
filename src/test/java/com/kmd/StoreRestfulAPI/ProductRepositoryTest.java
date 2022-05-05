package com.kmd.StoreRestfulAPI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    public void before() {
        productRepository.save(new Product("Ice Cream"));
        productRepository.save(new Product("Brownie"));
    }


    @Test
    public void saveProducts() {
        List<Product> products = List.of(new Product("Cake"), new Product("Brownie"));

        List<Product> savedProducts = products.stream().map(product -> productRepository.save(product)).collect(Collectors.toList());

        assertEquals(2, savedProducts.size());
    }

    @Test
    public void findAllProducts() {
        assertEquals(2,productRepository.findAll().size());
    }

    @Test
    public void findProductsByName() {
        List<Product> brownies = productRepository.findProductsByName("Brownie");

        assertEquals(1,brownies.size());
    }

}
