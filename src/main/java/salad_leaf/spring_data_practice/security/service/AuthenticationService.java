package salad_leaf.spring_data_practice.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import salad_leaf.spring_data_practice.security.dto.JwtAuthenticationResponse;
import salad_leaf.spring_data_practice.security.dto.SignInRequest;
import salad_leaf.spring_data_practice.security.dto.SignUpRequest;
import salad_leaf.spring_data_practice.security.dto.UpdateUserRoleRequest;
import salad_leaf.spring_data_practice.security.entity.Role;
import salad_leaf.spring_data_practice.security.entity.User;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userService.createUser(user);
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));

                User user = userService.getByUsername(request.getUsername());

                if (!user.isAccountNonLocked()) {
                    throw new RuntimeException("Аккаунт заблокирован");
                }

                userService.resetFailedAttempts(user);

                String jwt = jwtService.generateToken(user);
                return new JwtAuthenticationResponse(jwt);

            } catch (BadCredentialsException e) {
                User user = userService.getByUsername(request.getUsername());
                userService.increaseFailedAttempts(user);
                throw new RuntimeException("Неверные учетные данные");
            }
    }

    public JwtAuthenticationResponse updateUserRoleAndRefreshToken(UpdateUserRoleRequest request) {
        User user = userService.grantAuthority(request.getUsername(), request.getRole());
        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

}
