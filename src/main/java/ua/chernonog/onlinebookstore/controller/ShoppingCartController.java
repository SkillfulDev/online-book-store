package ua.chernonog.onlinebookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.chernonog.onlinebookstore.dto.request.cartitem.CartItemRequestDto;
import ua.chernonog.onlinebookstore.dto.response.cartitem.CartItemResponseDto;
import ua.chernonog.onlinebookstore.dto.response.shoppingcart.ShoppingCartResponseDto;
import ua.chernonog.onlinebookstore.entity.User;
import ua.chernonog.onlinebookstore.service.ShoppingCartService;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Tag(name = "ShoppingCart management", description = "Endpoints for managing shoppingCart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Operation(summary = "Add books to shopping cart", description = "Add books to shopping cart")
    public CartItemResponseDto add(
            @Valid @RequestBody CartItemRequestDto cartItemRequestDto,
            @AuthenticationPrincipal User currentUser) {
        return shoppingCartService.save(cartItemRequestDto, currentUser);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Operation(summary = "Get user shopping cart", description = "Get user shopping cart")
    public ShoppingCartResponseDto get(
            @AuthenticationPrincipal User currentUser) {
        return shoppingCartService.getById(currentUser.getId());
    }

    @PutMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Operation(summary = " Update the books quantity in user shopping cart",
            description = "Update the books quantity in my shopping cart")
    public CartItemResponseDto update(
            @PathVariable Long cartItemId,
            @Valid @RequestBody CartItemRequestDto cartItemRequestDto,
            @AuthenticationPrincipal User currentUser) {
        return shoppingCartService.updateById(
                currentUser.getId(),
                cartItemId,
                cartItemRequestDto.getQuantity()
        );
    }

    @DeleteMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Operation(summary = "Delete cart item in user shopping cart",
            description = "Delete cart item in user shopping cart")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long cartItemId,
            @AuthenticationPrincipal User currentUser) {
        shoppingCartService.deleteById(currentUser.getId(), cartItemId);
    }
}
