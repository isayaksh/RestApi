package study.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.restapi.domain.cartitem.CartItem;

import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // @Query("select c from CartItem c where c.member_id =:memberId")
    List<CartItem> findAllByMemberId(Long memberId);
}
