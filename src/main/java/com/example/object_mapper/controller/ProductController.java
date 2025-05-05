package com.example.object_mapper.controller;

import com.example.object_mapper.model.Product;
import com.example.object_mapper.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;
    private final ObjectMapper mapper;

    public ProductController(ProductService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<Product> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Product create(@RequestBody String json) throws JsonProcessingException {
        Product product = mapper.readValue(json, Product.class);
        return service.save(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody String json) throws JsonProcessingException {
        Product product = mapper.readValue(json, Product.class);
        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
