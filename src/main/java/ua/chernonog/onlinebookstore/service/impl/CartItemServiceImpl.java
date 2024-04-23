package ua.chernonog.onlinebookstore.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.chernonog.onlinebookstore.dto.request.cartitem.CartItemRequestDto;
import ua.chernonog.onlinebookstore.entity.CartItem;
import ua.chernonog.onlinebookstore.entity.ShoppingCart;
import ua.chernonog.onlinebookstore.exception.DuplicateEntityException;
import ua.chernonog.onlinebookstore.exception.EntityNotFoundException;
import ua.chernonog.onlinebookstore.mapper.CartItemMapper;
import ua.chernonog.onlinebookstore.repository.book.BookRepository;
import ua.chernonog.onlinebookstore.repository.cartitem.CartItemRepository;
import ua.chernonog.onlinebookstore.service.CartItemService;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;

    @Transactional
    @Override
    public CartItem save(
            CartItemRequestDto cartItemRequestDto,
            ShoppingCart shoppingCartFromDb
    ) {
        cartItemRepository.findBookInCart(
                        cartItemRequestDto.getBookId(),
                        shoppingCartFromDb.getId()
                )
                .ifPresent(s -> {
                    throw new DuplicateEntityException("This book has already been added");
                });

        CartItem cartItem = cartItemMapper.toModel(cartItemRequestDto);
        cartItem.setBook(bookRepository.findById(cartItemRequestDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find book with id " + cartItemRequestDto.getBookId())));
        cartItem.setShoppingCart(shoppingCartFromDb);

        return cartItemRepository.save(cartItem);
    }

    @Transactional
    @Override
    public void deleteById(CartItem cartItem) {
        if (!cartItemRepository.existsById(cartItem.getId())) {
            throw new EntityNotFoundException("Can't find cartItem with id " + cartItem.getId());
        }
        cartItemRepository.delete(cartItem);
    }

    @Transactional
    @Override
    public CartItem updateById(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }
}
