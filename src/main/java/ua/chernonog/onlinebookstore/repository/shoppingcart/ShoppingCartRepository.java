package ua.chernonog.onlinebookstore.repository.shoppingcart;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.chernonog.onlinebookstore.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @EntityGraph(attributePaths = {"cartItems.book"})
    Optional<ShoppingCart> findByUserId(Long id);

    @Override
    @EntityGraph(attributePaths = {"cartItems"})
    Optional<ShoppingCart> findById(Long id);
}
