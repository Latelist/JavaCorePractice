package salad_leaf.spring_mvc_library.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, Object> createResponseBody(HttpStatus status, String message, String path) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", path);
        return body;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.resolve(ex.getStatusCode().value());
        if (status == null) status = HttpStatus.INTERNAL_SERVER_ERROR;

        return new ResponseEntity<>(
                createResponseBody(status, ex.getReason(), request.getRequestURI()),
                status
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                createResponseBody(HttpStatus.BAD_REQUEST, "Invalid parameter type: " + ex.getName(), request.getRequestURI()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                createResponseBody(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                createResponseBody(HttpStatus.FORBIDDEN, "У вас нет доступа к этому ресурсу", request.getRequestURI()),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleAuthentication(AuthenticationException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                createResponseBody(HttpStatus.UNAUTHORIZED, "Пожалуйста, войдите в систему", request.getRequestURI()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllOtherExceptions(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                createResponseBody(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getRequestURI()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}