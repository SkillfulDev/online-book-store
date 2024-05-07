package ua.chernonog.onlinebookstore.dto.response.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;
import ua.chernonog.onlinebookstore.dto.response.orderitem.OrderItemResponseDto;
import ua.chernonog.onlinebookstore.entity.Status;

@Data
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private Set<OrderItemResponseDto> orderItems;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private Status status;
}
