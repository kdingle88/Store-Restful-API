package com.kmd.StoreRestfulAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    public void testGetProductsByType() throws Exception {
        List<Product> products = List.of(new Product(1L,"Brownie"), new Product(2L,"Brownie"));
        when(productService.getProductsByType("Brownie")).thenReturn(products);

        this.mockMvc.perform(get("/products?type=Brownie")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"id\":1,\"type\":\"Brownie\"},{\"id\":2,\"type\":\"Brownie\"}]")));
    }

    @Test
    public void testAddProduct() throws Exception {
        Product mockProduct = new Product(1L,"Pie");

        when(productService.addProduct(any(Product.class))).thenReturn(mockProduct);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"type\":\"Pie\"}");

        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"type\":\"Pie\"}")));
    }
}
