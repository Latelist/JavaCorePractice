package com.example.object_mapper.repository;

import com.example.object_mapper.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
