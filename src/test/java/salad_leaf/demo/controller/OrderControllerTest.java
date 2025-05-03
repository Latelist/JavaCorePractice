package salad_leaf.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import salad_leaf.demo.model.Order;
import salad_leaf.demo.model.OrderStatus;
import salad_leaf.demo.model.User;
import salad_leaf.demo.service.OrderService;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnAllOrders() throws Exception {
        Order order = new Order("Guitar", 1000);
        Mockito.when(orderService.findAllOrders()).thenReturn(List.of(order));

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].product").value("Guitar"))
                .andExpect(jsonPath("$[0].price").value(1000));
    }

    @Test
    void shouldReturnUserOrders() throws Exception {
        UUID userId = UUID.randomUUID();
        Order order = new Order("Piano", 1500);
        Mockito.when(orderService.findAllUserOrders(userId)).thenReturn(List.of(order));

        mockMvc.perform(get("/orders/user/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].product").value("Piano"));
    }

    @Test
    void shouldReturnOrderDetails() throws Exception {
        Order order = new Order("Drum", 900);
        order.setStatus(OrderStatus.SHIPPED);
        Mockito.when(orderService.findOrderById(1L)).thenReturn(order);

        mockMvc.perform(get("/orders/order/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product").value("Drum"))
                .andExpect(jsonPath("$.status").value("SHIPPED"));
    }

    @Test
    void shouldCreateOrder() throws Exception {
        UUID userId = UUID.randomUUID();
        Order order = new Order("Violin", 1200);
        Mockito.when(orderService.createOrder(Mockito.eq(userId), Mockito.any(Order.class)))
                .thenReturn(order);

        mockMvc.perform(post("/orders/user/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product").value("Violin"))
                .andExpect(jsonPath("$.price").value(1200));
    }

    @Test
    void shouldUpdateOrderStatus() throws Exception {
        Order updated = new Order("Synth", 3000);
        updated.setStatus(OrderStatus.CLOSED);
        Mockito.when(orderService.updateOrder(Mockito.eq(5L), Mockito.any(Order.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/orders/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CLOSED"));
    }

    @Test
    void shouldDeleteOrder() throws Exception {
        mockMvc.perform(delete("/orders/99"))
                .andExpect(status().isOk());

        Mockito.verify(orderService).deleteOrder(99L);
    }
}
