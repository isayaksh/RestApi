package study.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.restapi.domain.cartitem.CartItem;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
