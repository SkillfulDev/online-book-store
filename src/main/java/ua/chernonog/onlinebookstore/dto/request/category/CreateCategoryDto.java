package ua.chernonog.onlinebookstore.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCategoryDto {
    private Long id;
    @NotBlank(message = "Name can't be empty")
    private String name;
    private String description;
}
