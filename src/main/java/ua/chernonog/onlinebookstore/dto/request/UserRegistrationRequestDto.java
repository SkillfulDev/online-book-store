package ua.chernonog.onlinebookstore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ua.chernonog.onlinebookstore.annotation.FieldMatch;

@Data
@FieldMatch.List({
        @FieldMatch(first = "password", second = "repeatPassword",
                message = "The password fields must match")
})
public class UserRegistrationRequestDto {
    @NotBlank(message = "Email cannot be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    @Length(min = 8, max = 20,
            message = "Password length should be at least 3 and not more then 20")
    private String password;
    @NotEmpty(message = "RepeatPassword cannot be empty")
    @Length(min = 8, max = 20,
            message = "Password length should be at least 3 and not more then 20")
    private String repeatPassword;
    @NotEmpty(message = "FirstName cannot be empty")
    @Length(min = 2, max = 30,
            message = "FirstName length should be at least 2 and not more then 30")
    private String firstName;
    @NotEmpty(message = "LastName cannot be empty")
    @Length(min = 2, max = 30,
            message = "LastName length should be at least 2 and not more then 30")
    private String lastName;
    private String shippingAddress;

}
