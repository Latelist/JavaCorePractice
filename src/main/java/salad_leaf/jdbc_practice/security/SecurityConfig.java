package salad_leaf.jdbc_practice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(auth ->
                            auth.requestMatchers("/home").permitAll()
                                    .requestMatchers("/books/**").hasRole("USER")
                                    .anyRequest().authenticated()
                    ).httpBasic(Customizer.withDefaults());
            return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService() {
            var userDetailsManager = new InMemoryUserDetailsManager();
            var user = User.withUsername("user")
                    .password("{noop}pass")
                    .roles("USER")
                    .build();
            userDetailsManager.createUser(user);
            return userDetailsManager;
        }
    }
