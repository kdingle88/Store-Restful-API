package com.kmd.StoreRestfulAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void getAllProductsShouldReturnListOfAllProducts() throws Exception {
        Product product = new Product(1L,"Cake");

        List<Product> products = List.of(product);
        when(productService.getAllProducts()).thenReturn(products);

        this.mockMvc.perform(get("/products")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"id\":1,\"type\":\"Cake\"}]")));
    }
}
