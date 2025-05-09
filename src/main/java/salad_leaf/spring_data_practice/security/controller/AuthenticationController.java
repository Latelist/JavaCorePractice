package salad_leaf.spring_data_practice.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import salad_leaf.spring_data_practice.security.dto.JwtAuthenticationResponse;
import salad_leaf.spring_data_practice.security.dto.SignInRequest;
import salad_leaf.spring_data_practice.security.dto.SignUpRequest;
import salad_leaf.spring_data_practice.security.dto.UpdateUserRoleRequest;
import salad_leaf.spring_data_practice.security.entity.Role;
import salad_leaf.spring_data_practice.security.entity.User;
import salad_leaf.spring_data_practice.security.service.AuthenticationService;
import salad_leaf.spring_data_practice.security.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @GetMapping
    public String welcome() {
        return "Это страничка авторизации. Войдите или зарегистрируйтесь";
    }

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @PutMapping("/grant-authority")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public JwtAuthenticationResponse grantAuthority(@RequestBody UpdateUserRoleRequest request) {
        return authenticationService.updateUserRoleAndRefreshToken(request);
    }

    @PutMapping("/unlock")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<String> unlockUser(@RequestParam String username) {
        userService.unlockUser(username);
        return ResponseEntity.ok("Пользователь разблокирован");
    }
}
