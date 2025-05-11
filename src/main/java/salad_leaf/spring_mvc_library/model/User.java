package salad_leaf.spring_mvc_library.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    private String email;
    private String githubId;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean accountNonLocked = true; // для блокировки при неудачных логинах
}
