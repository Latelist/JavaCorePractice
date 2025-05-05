package com.example.object_mapper.model;

import com.example.object_mapper.model.Customer;
import com.example.object_mapper.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    private Long orderId;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    private List<Product> products;

    private LocalDateTime orderDate;
    private String shippingAddress;
    private BigDecimal totalPrice;
    private String orderStatus;

    public Order() {}
}
