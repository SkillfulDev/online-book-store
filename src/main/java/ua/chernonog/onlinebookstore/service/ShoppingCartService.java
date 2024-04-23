package ua.chernonog.onlinebookstore.service;

import ua.chernonog.onlinebookstore.dto.request.cartitem.CartItemRequestDto;
import ua.chernonog.onlinebookstore.dto.response.cartitem.CartItemResponseDto;
import ua.chernonog.onlinebookstore.dto.response.shoppingcart.ShoppingCartResponseDto;
import ua.chernonog.onlinebookstore.entity.CartItem;
import ua.chernonog.onlinebookstore.entity.ShoppingCart;
import ua.chernonog.onlinebookstore.entity.User;

public interface ShoppingCartService {

    CartItemResponseDto update(ShoppingCart shoppingCart, CartItem savedCartItem);

    ShoppingCartResponseDto getById(Long userId);

    void deleteById(Long userId, Long cartItemId);

    CartItemResponseDto save(CartItemRequestDto cartItemRequestDto, User currentUser);

    CartItemResponseDto updateById(Long userId, Long cartItemId, int quantity);
}
