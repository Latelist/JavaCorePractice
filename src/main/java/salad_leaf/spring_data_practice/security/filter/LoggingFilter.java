package salad_leaf.spring_data_practice.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        long start = System.currentTimeMillis();

        // Пропускаем дальше по цепочке
        filterChain.doFilter(request, response);

        long duration = System.currentTimeMillis() - start;

        // Извлекаем текущую аутентификацию
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = "Anonymous";
        String roles = "-";

        if (authentication != null && authentication.isAuthenticated()) {
            username = authentication.getName();
            roles = authentication.getAuthorities().toString();
        }

        // Логируем инфу
        log.info(
                "[{}] {} {} | User: {} | Roles: {} | Status: {} | Reason: {} | {} ms",
                request.getMethod(),
                request.getRequestURI(),
                request.getProtocol(),
                username,
                roles,
                response.getStatus(),
                getReasonPhrase(response.getStatus()),
                duration
        );
    }

    private String getReasonPhrase(int status) {
        return switch (status) {
            case 200 -> "OK";
            case 201 -> "Created";
            case 204 -> "No Content";
            case 400 -> "Bad Request";
            case 401 -> "Unauthorized";
            case 403 -> "Forbidden";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            default -> "Unknown";
        };
    }
}
