package ua.chernonog.onlinebookstore.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.URL;

@Data
public class CreateBookRequestDto {
    private Long id;
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Author cannot be empty")
    private String author;
    @ISBN(message = "Invalid ISBN format")
    @NotBlank(message = "ISBN cannot be empty")
    private String isbn;
    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.00", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;
    @NotBlank
    @Size(max = 255, message = "Description length must be less than 100 characters")
    private String description;
    @URL(message = "Cover image must be a valid URL")
    private String coverImage;
}
