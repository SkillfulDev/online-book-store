package ua.chernonog.onlinebookstore.dto.request.cartitem;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CartItemRequestDto {
    private Long bookId;
    @Min(value = 1, message = "Minimum quantity should be at least 1")
    private int quantity;
}
