package com.kmd.StoreRestfulAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByType(String type) {
        return productRepository.findProductsByType(type);
    }

    public Product addProduct(Product product) {
        productRepository.save(product);
        return product;
    }
}
