package ua.chernonog.onlinebookstore.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserLoginRequestDto {
    @NotBlank(message = "Email cannot be empty")
    @Email
    private String email;
    @NotBlank(message = "Password cannot be empty")
    @Length(min = 8, max = 20,
            message = "Password length should be at least 8 and not more then 20")
    private String password;
}
