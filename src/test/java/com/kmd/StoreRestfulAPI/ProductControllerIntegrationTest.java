package com.kmd.StoreRestfulAPI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    public void before() {
        productRepository.save(new Product(1L,"Ice Cream"));
        productRepository.save(new Product(2L,"Brownie"));
    }

    @Test
    public void shouldReturnAllProducts() throws Exception{
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/products",
                String.class)).isEqualTo("[{\"id\":1,\"name\":\"Ice Cream\"},{\"id\":2,\"name\":\"Brownie\"}]");
    }
}


