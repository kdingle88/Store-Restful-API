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
        List<Product> products = List.of(new Product("Cake"), new Product("Brownie"), new Product("Brownie"), new Product("Ice Cream"));

        products.stream().forEach(product -> productRepository.save(product));
    }

    @Test
    public void findAllProducts() {
        assertEquals(4,productRepository.findAll().size());
    }

    @Test
    public void findProductsByName() {
        List<Product> brownies = productRepository.findProductsByType("Brownie");

        assertEquals(2,brownies.size());
    }

}
