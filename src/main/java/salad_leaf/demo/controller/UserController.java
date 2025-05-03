package salad_leaf.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import salad_leaf.demo.model.User;
import salad_leaf.demo.model.Views;
import salad_leaf.demo.repository.UserRepository;
import salad_leaf.demo.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @JsonView(Views.UserSummary.class)
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @JsonView(Views.UserDetails.class)
    @GetMapping("/{id}")
    public User getUserDetails(@PathVariable UUID id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }


}
