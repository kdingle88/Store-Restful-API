package com.kmd.StoreRestfulAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllProducts() throws Exception{
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/products",
                String.class)).isEqualTo("[{\"id\":\"1\", \"name\":\"Cake\"}]");
    }
}


