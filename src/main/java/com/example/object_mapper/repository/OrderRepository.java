package com.example.object_mapper.repository;

import com.example.object_mapper.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
