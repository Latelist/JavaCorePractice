package salad_leaf.demo.service;

import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import salad_leaf.demo.model.Order;
import salad_leaf.demo.model.User;
import salad_leaf.demo.repository.OrderRepository;
import salad_leaf.demo.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, UserService userService){
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public Order createOrder(UUID id, Order order) {
        User user = userService.findUserById(id);
        order.setUser(user);
        return orderRepository.save(order);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
    public List<Order> findAllUserOrders(UUID id) {
        User user = userService.findUserById(id);
        return orderRepository.findByUser(user);
    }

    public Order findOrderById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    public Order updateOrder(Long id, Order order) {
        Order existingOrder = findOrderById(id);
        existingOrder.setStatus(order.getStatus());

        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
