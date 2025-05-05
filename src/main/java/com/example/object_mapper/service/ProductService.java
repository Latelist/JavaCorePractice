package com.example.object_mapper.service;

import com.example.object_mapper.exception.ResourceNotFoundException;
import com.example.object_mapper.model.Product;
import com.example.object_mapper.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository repo) {
        this.productRepository = repo;
    }

    public List<Product> findAll() { return productRepository.findAll(); }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public Product save(Product product) { return productRepository.save(product); }

    public Product update(Long id, Product product) {
        Product existing = findById(id);
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setQuantityInStock(product.getQuantityInStock());
        return productRepository.save(existing);
    }

    public void delete(Long id) { productRepository.deleteById(id); }
}
