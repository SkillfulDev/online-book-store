package ua.chernonog.onlinebookstore.service;

import ua.chernonog.onlinebookstore.dto.request.cartitem.CartItemRequestDto;
import ua.chernonog.onlinebookstore.entity.CartItem;
import ua.chernonog.onlinebookstore.entity.ShoppingCart;

public interface CartItemService {
    CartItem save(
            CartItemRequestDto cartItemRequestDto,
            ShoppingCart shoppingCartFromDb
    );

    CartItem updateById(CartItem cartItem);

    void deleteById(CartItem cartItem);
}
