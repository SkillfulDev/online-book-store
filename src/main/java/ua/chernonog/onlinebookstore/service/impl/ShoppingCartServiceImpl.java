package ua.chernonog.onlinebookstore.service.impl;

import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.chernonog.onlinebookstore.dto.request.cartitem.CartItemRequestDto;
import ua.chernonog.onlinebookstore.dto.response.cartitem.CartItemResponseDto;
import ua.chernonog.onlinebookstore.dto.response.shoppingcart.ShoppingCartResponseDto;
import ua.chernonog.onlinebookstore.entity.CartItem;
import ua.chernonog.onlinebookstore.entity.ShoppingCart;
import ua.chernonog.onlinebookstore.entity.User;
import ua.chernonog.onlinebookstore.exception.EntityNotFoundException;
import ua.chernonog.onlinebookstore.mapper.CartItemMapper;
import ua.chernonog.onlinebookstore.mapper.ShoppingCartMapper;
import ua.chernonog.onlinebookstore.repository.shoppingcart.ShoppingCartRepository;
import ua.chernonog.onlinebookstore.service.CartItemService;
import ua.chernonog.onlinebookstore.service.ShoppingCartService;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;
    private final CartItemService cartItemService;

    @Transactional
    @Override
    public CartItemResponseDto save(CartItemRequestDto cartItemRequestDto, User currentUser) {
        ShoppingCart shoppingCart = findShoppingCartIfExist(currentUser.getId())
                .orElseGet(() ->
                        shoppingCartRepository.save(createShoppingCart(currentUser)));

        CartItem savedCartItem = cartItemService.save(cartItemRequestDto, shoppingCart);
        return update(shoppingCart, savedCartItem);
    }

    @Override
    public ShoppingCartResponseDto getById(Long userId) {
        return shoppingCartMapper.toDto(findShoppingCartIfExist(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("User didn't make any orders")));

    }

    @Transactional
    @Override
    public void deleteById(Long userId, Long cartItemId) {
        ShoppingCart shoppingCardFromDb = getShoppingCartOrThrow(userId);
        shoppingCardFromDb.getCartItems().stream()
                .filter(cartItem -> cartItem.getId().equals(cartItemId))
                .findFirst()
                .ifPresentOrElse(
                        cartItemService::deleteById,
                        () -> {
                            throw new EntityNotFoundException(
                                    "CartItem not found with id: " + cartItemId
                            );
                        });

    }

    @Transactional
    @Override
    public CartItemResponseDto updateById(Long userId, Long cartItemId, int quantity) {
        ShoppingCart shoppingCart = getShoppingCartOrThrow(userId);
        return shoppingCart.getCartItems().stream()
                .filter(cartItem -> cartItem.getId().equals(cartItemId))
                .findFirst()
                .map(cartItem -> {
                    cartItem.setQuantity(quantity);
                    cartItemService.updateById(cartItem);
                    return cartItemMapper.toDto(cartItem);
                })
                .orElseThrow(() ->
                        new EntityNotFoundException("CartItem not found with id: "
                                + cartItemId));
    }

    @Transactional
    public CartItemResponseDto update(ShoppingCart shoppingCart, CartItem savedCartItem) {
        shoppingCart.getCartItems().add(savedCartItem);
        shoppingCartRepository.save(shoppingCart);
        return cartItemMapper.toDto(savedCartItem);
    }

    private Optional<ShoppingCart> findShoppingCartIfExist(Long userId) {
        return shoppingCartRepository.findByUserId(userId);
    }

    private ShoppingCart createShoppingCart(User user) {
        ShoppingCart newShoppingCart = new ShoppingCart();
        newShoppingCart.setUser(user);
        newShoppingCart.setCartItems(new HashSet<>());
        return newShoppingCart;
    }

    private ShoppingCart getShoppingCartOrThrow(Long userId) {
        return findShoppingCartIfExist(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Shopping cart not found for user id: " + userId)
                );
    }
}
