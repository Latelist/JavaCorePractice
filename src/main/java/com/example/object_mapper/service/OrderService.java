package com.example.object_mapper.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import com.example.object_mapper.exception.ResourceNotFoundException;
import com.example.object_mapper.model.Customer;
import com.example.object_mapper.model.Order;
import com.example.object_mapper.model.Product;
import com.example.object_mapper.repository.CustomerRepository;
import com.example.object_mapper.repository.OrderRepository;
import com.example.object_mapper.repository.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        ProductRepository productRepository,
                        ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    public Order placeOrder(Order order) {
        Customer customer = customerRepository.findById(order.getCustomer().getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Не нашёл такого клиента"));

        List<Product> products = order.getProducts().stream()
                .map(p -> productRepository.findById(p.getProductId())
                        .orElseThrow(() -> new ResourceNotFoundException("Не нашёл продукта: " + p.getProductId())))
                .toList();

        BigDecimal total = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setCustomer(customer);
        order.setProducts(products);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(total);
        order.setOrderStatus("Создан");

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Не нашёл заказа"));
    }
}
