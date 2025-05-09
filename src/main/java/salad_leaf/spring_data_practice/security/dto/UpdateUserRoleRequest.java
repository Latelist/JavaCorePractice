package salad_leaf.spring_data_practice.security.dto;

import lombok.Data;
import salad_leaf.spring_data_practice.security.entity.Role;

@Data
public class UpdateUserRoleRequest {
    private String username;
    private Role role;
}
