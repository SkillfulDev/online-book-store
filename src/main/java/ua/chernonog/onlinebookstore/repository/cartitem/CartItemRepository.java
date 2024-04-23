package ua.chernonog.onlinebookstore.repository.cartitem;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.chernonog.onlinebookstore.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT c FROM CartItem c WHERE c.book.id = :bookId "
            + "AND c.shoppingCart.id = :shoppingCartId")
    Optional<CartItem> findBookInCart(
            @Param("bookId") Long bookId,
            @Param("shoppingCartId") Long shoppingCartId
    );
}
