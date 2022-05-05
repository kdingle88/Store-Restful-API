package com.kmd.StoreRestfulAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Test
    public void testFindAll() throws Exception {
        List<Product> products = List.of(new Product(1L, "Cake"), new Product(2L,"Brownie"), new Product(3L,"Ice Cream"));

        when(productRepository.findAll()).thenReturn(products);

        assertEquals(3,productService.getAllProducts().size());

    }
}
