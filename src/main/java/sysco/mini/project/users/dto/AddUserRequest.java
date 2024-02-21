package sysco.mini.project.users.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sysco.mini.project.users.enums.UserRole;

@NoArgsConstructor
public class AddUserRequest {
    @Getter @Setter
    @NotBlank(message = "Name is required")
    private String name;

    @Getter @Setter
    @Email(message = "Not a valid email")
    private String email;

    @Getter @Setter
    @NotNull(message = "User role is required")
    private UserRole role;
}
