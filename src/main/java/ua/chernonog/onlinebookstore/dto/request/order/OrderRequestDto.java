package ua.chernonog.onlinebookstore.dto.request.order;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderRequestDto {
    @NotBlank(message = "shippingAddress can't be  empty")
    private String shippingAddress;
}
