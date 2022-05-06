package com.kmd.StoreRestfulAPI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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
                String.class)).isEqualTo("[{\"id\":1,\"type\":\"Ice Cream\"},{\"id\":2,\"type\":\"Brownie\"}]");
    }
    @Test
    public void shouldReturnProductsByType() throws Exception{
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/products?type=Brownie",
                String.class)).isEqualTo("[{\"id\":2,\"type\":\"Brownie\"}]");

    }

    @Test
    public void shouldAddProduct() throws Exception {

        Product product = new Product(3L,"Pie");

        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/products?type=Brownie",
                product,String.class)).isEqualTo("{\"id\":3,\"type\":\"Pie\"}");

        assertEquals(3,productRepository.findAll().size());
    }
}


