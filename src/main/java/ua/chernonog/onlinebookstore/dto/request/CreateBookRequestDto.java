package ua.chernonog.onlinebookstore.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class CreateBookRequestDto {
    private Long id;
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotEmpty(message = "Author cannot be empty")
    private String author;
    @NotEmpty(message = "ISBN cannot be empty")
    @Pattern(regexp = "^(?:\\d{9}[\\dX]|97[89]\\d{10})$", message = "Invalid ISBN format")
    private String isbn;
    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.00", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;
    @NotEmpty
    @Size(max = 100, message = "Description length must be less than 100 characters")
    private String description;
    @URL(message = "Cover image must be a valid URL")
    private String coverImage;
}
