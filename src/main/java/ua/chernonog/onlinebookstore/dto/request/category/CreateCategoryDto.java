package ua.chernonog.onlinebookstore.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCategoryDto {
    private Long id;
    @NotBlank(message = "Name can't be empty")
    @Size(min = 3, message = "Category size should be at least 3")
    private String name;
    private String description;
}
