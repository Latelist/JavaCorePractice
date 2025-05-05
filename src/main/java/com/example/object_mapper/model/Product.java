package com.example.object_mapper.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    private Long productId;

    private String name;
    private String description;
    private BigDecimal price;
    private int quantityInStock;

    public Product(){}
}
