package com.kmd.StoreRestfulAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public @ResponseBody List<Product> getProducts(@RequestParam(required = false) String type) {
        if(type == null) {
            return productService.getAllProducts();
        }

        return productService.getProductsByType(type);

    }

}
