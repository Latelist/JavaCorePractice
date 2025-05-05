package com.example.object_mapper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.object_mapper.model.Customer;
import com.example.object_mapper.model.Product;
import com.example.object_mapper.repository.CustomerRepository;
import com.example.object_mapper.repository.ProductRepository;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private ProductRepository productRepository;
    @Autowired private CustomerRepository customerRepository;

    @Test
    public void shouldPlaceOrder() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Анна");
        customer.setLastName("Каренина");
        customer.setEmail("anna@example.com");
        customer.setContactNumber("+7 999 555-55-55");
        customer = customerRepository.save(customer);

        Product product = new Product();
        product.setName("Книга");
        product.setDescription("Роман");
        product.setPrice(new BigDecimal("1000.00"));
        product.setQuantityInStock(5);
        product = productRepository.save(product);

        String orderJson = "{" +
                "\"customer\": { \"customerId\": " + customer.getCustomerId() + " }," +
                "\"products\": [{ \"productId\": " + product.getProductId() + " }]," +
                "\"shippingAddress\": \"ул. Пушкина, д. 1\"" +
                "}";

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value(1000.00))
                .andExpect(jsonPath("$.orderStatus").value("Создан"));
    }
}
