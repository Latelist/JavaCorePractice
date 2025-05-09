package salad_leaf.spring_data_practice.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {

    @NotBlank
    @Size(min = 3, max = 20, message = "Придумайте имя длиной от 3 до 20 символов")
    private String username;

    @Size(min = 4, max = 100, message = "Придумайте пароль от 4 до 100 символов")
    private String password;

}
