package az.edu.turing.msaccount.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String password;

    @Transient
    private String confirmPassword;

    @Email(message = "Invalid email format")
    private String email;
}
