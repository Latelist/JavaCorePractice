package salad_leaf.spring_mvc_library.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        long start = System.currentTimeMillis();

        // Достаём данные пользователя
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (auth != null && auth.isAuthenticated()) ? auth.getName() : "anonymous";
        List<String> roles = (auth != null && auth.getAuthorities() != null)
                ? auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
                : List.of();

        // Продолжаем цепочку фильтров
        filterChain.doFilter(request, response);

        long duration = System.currentTimeMillis() - start;

        log.info("📥 {} {} | 🧑‍💻 {} | 🎭 Roles: {} | 📤 Status: {} | ⏱️ {} ms",
                request.getMethod(),
                request.getRequestURI(),
                username,
                roles,
                response.getStatus(),
                duration
        );
    }
}
