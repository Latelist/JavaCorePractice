package salad_leaf.spring_data_practice.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import salad_leaf.spring_data_practice.security.entity.Role;
import salad_leaf.spring_data_practice.security.entity.User;
import salad_leaf.spring_data_practice.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private static final int MAX_FAILED_ATTEMPTS = 5;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Такой пользователь уже есть");
        }
        return save(user);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Я не знаю такого пользователя"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User grantAuthority(String username, Role role) {
        User existingUser = getByUsername(username);
        existingUser.setRole(role);
        return userRepository.save(existingUser);
    }
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public void increaseFailedAttempts(User user) {
        user.setFailedAttempts(user.getFailedAttempts() + 1);
        if (user.getFailedAttempts() >= MAX_FAILED_ATTEMPTS) {
            user.setLocked(true);
        }
        userRepository.save(user);
    }

    public void resetFailedAttempts(User user) {
        user.setFailedAttempts(0);
        userRepository.save(user);
    }

    public void unlockUser(String username) {
        User user = getByUsername(username);
        user.setLocked(false);
        user.setFailedAttempts(0);
        userRepository.save(user);
    }
}
