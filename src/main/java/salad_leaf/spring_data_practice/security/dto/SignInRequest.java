package salad_leaf.spring_data_practice.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInRequest {

    @NotBlank
    @Size(min = 3, max = 20, message = "В имени может быть от 3 до 20 символов")
    private String username;

    @NotBlank
    @Size(min = 4, max = 100, message = "В пароле может быть от 4 до 100 символов")
    private String password;
}
