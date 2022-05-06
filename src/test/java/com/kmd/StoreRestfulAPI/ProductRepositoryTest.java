package com.kmd.StoreRestfulAPI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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

    @Test
    public void saveProduct() {
        Product savedProduct = productRepository.save(new Product(1L, "Candy"));

        assertEquals(1L,savedProduct.getId());
    }
}
