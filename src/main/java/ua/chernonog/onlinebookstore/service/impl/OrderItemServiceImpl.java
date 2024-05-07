package ua.chernonog.onlinebookstore.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.chernonog.onlinebookstore.dto.response.orderitem.OrderItemResponseDto;
import ua.chernonog.onlinebookstore.entity.Book;
import ua.chernonog.onlinebookstore.entity.Order;
import ua.chernonog.onlinebookstore.entity.OrderItem;
import ua.chernonog.onlinebookstore.exception.EntityNotFoundException;
import ua.chernonog.onlinebookstore.mapper.OrderItemMapper;
import ua.chernonog.onlinebookstore.repository.order.OrderRepository;
import ua.chernonog.onlinebookstore.repository.orderitem.OrderItemRepository;
import ua.chernonog.onlinebookstore.service.OrderItemService;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderRepository orderRepository;

    @Override
    public Set<OrderItem> save(Order savedOrder, Map<Book, Integer> bookFromCartItem) {
        return createOrderItems(savedOrder, bookFromCartItem);
    }

    @Override
    public List<OrderItemResponseDto> getAllOrderItem(Long orderId) {
        List<OrderItem> orderItemsFromDb = orderItemRepository.findByOrderId(orderId);

        if (orderItemsFromDb.isEmpty()) {
            throw new EntityNotFoundException("No order items found for order with id " + orderId);
        }

        return orderItemsFromDb.stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemResponseDto getItem(Long orderId, Long itemId) {
        boolean isOrderExists = orderRepository.existsById(orderId);
        if (!isOrderExists) {
            throw new EntityNotFoundException("Order with id " + orderId + " not found");
        }

        Optional<OrderItem> orderItemFromDb = orderItemRepository
                .findByIdAndOrderId(itemId, orderId);

        return orderItemFromDb
                .map(orderItemMapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("Item with id " + itemId + " not found"));
    }

    private Set<OrderItem> createOrderItems(Order savedOrder, Map<Book, Integer> bookFromCartItem) {
        return bookFromCartItem.entrySet().stream()
                .map(entry -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setBook(entry.getKey());
                    orderItem.setQuantity(entry.getValue());
                    orderItem.setOrder(savedOrder);
                    orderItem.setPrice(entry.getKey().getPrice());
                    return orderItemRepository.save(orderItem);
                })
                .collect(Collectors.toSet());
    }
}
