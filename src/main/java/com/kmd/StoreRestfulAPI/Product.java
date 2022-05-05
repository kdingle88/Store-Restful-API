package com.kmd.StoreRestfulAPI;

import javax.persistence.*;

@Entity
public class Product {

    @SequenceGenerator(name = "product_sequence",sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @Id
    private Long id;
    private String type;

    public Product(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Product(String type) {
        this.type = type;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String name) {
        this.type = name;
    }
}
