package ua.chernonog.onlinebookstore.service;

import java.util.List;
import ua.chernonog.onlinebookstore.dto.request.order.OrderRequestDto;
import ua.chernonog.onlinebookstore.dto.request.order.OrderStatusRequestDto;
import ua.chernonog.onlinebookstore.dto.response.order.OrderResponseDto;
import ua.chernonog.onlinebookstore.entity.User;

public interface OrderService {
    void place(OrderRequestDto order, User currentUser);

    List<OrderResponseDto> getAllOrders(User currentUser);

    void updateStatus(OrderStatusRequestDto orderStatus, Long orderId, Long userId);
}
