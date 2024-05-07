package ua.chernonog.onlinebookstore.repository.orderitem;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.chernonog.onlinebookstore.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);

    Optional<OrderItem> findByIdAndOrderId(Long itemId, Long orderId);
}
