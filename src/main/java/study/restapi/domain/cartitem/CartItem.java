package study.restapi.domain.cartitem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.restapi.domain.item.Item;
import study.restapi.domain.member.Member;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class CartItem {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cart_item_id", updatable = false)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public static CartItem createCartItem(Member member, Item item){
        CartItem cartItem = new CartItem();
        cartItem.member = member;
        cartItem.item = item;
        return cartItem;
    }
}
