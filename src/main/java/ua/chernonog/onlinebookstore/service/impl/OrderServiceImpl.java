package ua.chernonog.onlinebookstore.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.chernonog.onlinebookstore.dto.request.order.OrderRequestDto;
import ua.chernonog.onlinebookstore.dto.request.order.OrderStatusRequestDto;
import ua.chernonog.onlinebookstore.dto.response.order.OrderResponseDto;
import ua.chernonog.onlinebookstore.entity.Book;
import ua.chernonog.onlinebookstore.entity.CartItem;
import ua.chernonog.onlinebookstore.entity.Order;
import ua.chernonog.onlinebookstore.entity.OrderItem;
import ua.chernonog.onlinebookstore.entity.ShoppingCart;
import ua.chernonog.onlinebookstore.entity.Status;
import ua.chernonog.onlinebookstore.entity.User;
import ua.chernonog.onlinebookstore.exception.EntityNotFoundException;
import ua.chernonog.onlinebookstore.mapper.OrderMapper;
import ua.chernonog.onlinebookstore.repository.order.OrderRepository;
import ua.chernonog.onlinebookstore.repository.shoppingcart.ShoppingCartRepository;
import ua.chernonog.onlinebookstore.service.OrderItemService;
import ua.chernonog.onlinebookstore.service.OrderService;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional
    @Override
    public void place(OrderRequestDto order, User currentUser) {
        ShoppingCart userShoppingCart = getUserShoppingCart(currentUser);
        BigDecimal totalPrice = calculateTotalPrice(userShoppingCart);
        Map<Book, Integer> booksFromCartItem = getBookToQuantityMap(userShoppingCart);

        Order newOrder = createNewOrder(order, currentUser, totalPrice);
        Order savedOrder = orderRepository.save(newOrder);

        Set<OrderItem> orderItems = orderItemService.save(savedOrder, booksFromCartItem);

        savedOrder.setOrderItems(orderItems);
        orderRepository.save(savedOrder);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderResponseDto> getAllOrders(User currentUser) {
        return orderRepository.findByUserId(currentUser.getId()).stream()
                .map(orderMapper::toDto).toList();
    }

    @Transactional
    @Override
    public void updateStatus(OrderStatusRequestDto orderStatus, Long orderId, Long userId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Order with id " + orderId + " not found"));
        order.setStatus(orderStatus.getStatus());
        orderRepository.save(order);
    }

    private Order createNewOrder(OrderRequestDto order, User currentUser, BigDecimal totalPrice) {
        Order newOrder = new Order();
        newOrder.setUser(currentUser);
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setStatus(Status.PENDING);
        newOrder.setShippingAddress(order.getShippingAddress());
        newOrder.setTotal(totalPrice);
        return newOrder;
    }

    private BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems().stream()
                .map(cartItem -> cartItem.getBook().getPrice()
                        .multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private ShoppingCart getUserShoppingCart(User currentUser) {
        return shoppingCartRepository.findByUserId(currentUser.getId()).orElseThrow(() ->
                new EntityNotFoundException(
                        "Can't find shopping cart for user " + currentUser.getId())
        );
    }

    private Map<Book, Integer> getBookToQuantityMap(ShoppingCart userShoppingCart) {
        Map<Book, Integer> bookFromCartItem = new HashMap<>();
        for (CartItem cartItem : userShoppingCart.getCartItems()) {
            bookFromCartItem.put(cartItem.getBook(), cartItem.getQuantity());
        }
        return bookFromCartItem;
    }
}
