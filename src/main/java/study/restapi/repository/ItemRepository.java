package study.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.restapi.domain.item.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {
}
