package salad_leaf.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salad_leaf.demo.model.Order;
import salad_leaf.demo.model.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
