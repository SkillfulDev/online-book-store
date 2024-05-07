package ua.chernonog.onlinebookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.chernonog.onlinebookstore.dto.request.order.OrderRequestDto;
import ua.chernonog.onlinebookstore.dto.request.order.OrderStatusRequestDto;
import ua.chernonog.onlinebookstore.dto.response.order.OrderResponseDto;
import ua.chernonog.onlinebookstore.entity.User;
import ua.chernonog.onlinebookstore.service.OrderService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Order management", description = "Endpoints for managing order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Operation(summary = "Place an order",
            description = "Place an order")
    public void placeOrder(
            @Valid @RequestBody OrderRequestDto order,
            @AuthenticationPrincipal User currentUser) {
        orderService.place(order, currentUser);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Operation(summary = "Get all orders",
            description = "Get all orders")
    public List<OrderResponseDto> getOrders(
            @AuthenticationPrincipal User currentUser) {
        return orderService.getAllOrders(currentUser);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Update an order status by id",
            description = "Update an order status by id")
    public void updateOrderStatus(
            @Valid @RequestBody OrderStatusRequestDto orderStatus,
            @PathVariable Long id,
            @AuthenticationPrincipal User currentUser) {
        orderService.updateStatus(orderStatus, id, currentUser.getId());
    }
}
