package salad_leaf.spring_data_practice.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salad_leaf.spring_data_practice.security.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
