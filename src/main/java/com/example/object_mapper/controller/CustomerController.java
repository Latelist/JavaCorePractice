package com.example.object_mapper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.object_mapper.model.Customer;
import com.example.object_mapper.service.CustomerService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolationException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final ObjectMapper objectMapper;
    private final Validator validator;

    public CustomerController(CustomerService customerService, ObjectMapper objectMapper, Validator validator) {
        this.customerService = customerService;
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody String json) throws JsonProcessingException {
        Customer customer = objectMapper.readValue(json, Customer.class);
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
