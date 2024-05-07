package ua.chernonog.onlinebookstore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import ua.chernonog.onlinebookstore.dto.response.orderitem.OrderItemResponseDto;
import ua.chernonog.onlinebookstore.entity.Book;
import ua.chernonog.onlinebookstore.entity.Order;
import ua.chernonog.onlinebookstore.entity.OrderItem;

public interface OrderItemService {
    Set<OrderItem> save(Order savedOrder, Map<Book, Integer> bookFromCartItem);

    List<OrderItemResponseDto> getAllOrderItem(Long orderId);

    OrderItemResponseDto getItem(Long orderId, Long itemId);
}
