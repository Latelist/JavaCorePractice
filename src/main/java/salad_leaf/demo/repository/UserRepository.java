package salad_leaf.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salad_leaf.demo.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
}
