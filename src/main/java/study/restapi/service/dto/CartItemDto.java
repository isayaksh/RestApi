package study.restapi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.LastModifiedDate;
import study.restapi.domain.cartitem.CartItem;
import study.restapi.domain.item.Book;
import study.restapi.domain.item.Item;
import study.restapi.domain.item.Movie;
import study.restapi.domain.member.Member;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Slf4j
public class CartItemDto {

    private LocalDateTime createdDate;
    private LocalDateTime lastModified;

    // Member
    private Long memberId;
    private String username;
    private String password;
    private int age;

    // Item
    private Long itemId;
    private String itemName;
    private int price;

    public static CartItemDto createCartItemDto(CartItem cartItem){
        CartItemDto dto = new CartItemDto();

        dto.createdDate = cartItem.getCreatedDate();
        dto.lastModified = cartItem.getLastModified();

        Member member = cartItem.getMember();
        dto.memberId = member.getId();
        dto.username = member.getUsername();
        dto.password = member.getPassword();
        dto.age = member.getAge();

        Item item = cartItem.getItem();
        dto.itemId = item.getId();
        dto.itemName = item.getName();
        dto.price = item.getPrice();

        return dto;
    }
}
