package ua.chernonog.onlinebookstore.dto.request.order;

import lombok.Data;
import ua.chernonog.onlinebookstore.entity.Status;

@Data
public class OrderStatusRequestDto {
    private Status status;
}
