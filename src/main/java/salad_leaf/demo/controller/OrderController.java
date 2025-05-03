package salad_leaf.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;
import salad_leaf.demo.model.Order;
import salad_leaf.demo.model.Views;
import salad_leaf.demo.service.OrderService;
import salad_leaf.demo.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/user/{userId}")
    public Order createOrder(@PathVariable UUID userId, @RequestBody Order order) {
        return orderService.createOrder(userId, order);
    }

    @JsonView(Views.OrderSummary.class)
    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.findAllOrders();
    }

    @JsonView(Views.OrderSummary.class)
    @GetMapping("/user/{userId}")
    public List<Order> getAllUserOrders(@PathVariable UUID userId){
        return orderService.findAllUserOrders(userId);
    }

    @JsonView(Views.OrderDetails.class)
    @GetMapping("/order/{id}")
    public Order findOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }


}
