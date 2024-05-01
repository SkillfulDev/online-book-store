package ua.chernonog.onlinebookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.chernonog.onlinebookstore.dto.response.orderitem.OrderItemResponseDto;
import ua.chernonog.onlinebookstore.service.OrderItemService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Order items management", description = "Endpoints for managing order items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping("/{id}/items")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Operation(summary = "Get all order items by order id",
            description = "Get all order items by order id")
    public List<OrderItemResponseDto> getAll(
            @PathVariable Long id
    ) {
        return orderItemService.getAllOrderItem(id);
    }

    @GetMapping("/{orderId}/items/{itemId}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Operation(summary = "Get item from order by id",
            description = "Get item from order by id")
    public OrderItemResponseDto getItem(
            @PathVariable Long orderId,
            @PathVariable Long itemId
    ) {
        return orderItemService.getItem(orderId, itemId);
    }
}
