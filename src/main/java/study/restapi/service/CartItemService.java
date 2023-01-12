package study.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.restapi.domain.cartitem.CartItem;
import study.restapi.domain.item.Item;
import study.restapi.domain.member.Member;
import study.restapi.exception.NotCorrespondingException;
import study.restapi.form.CartItemForm;
import study.restapi.repository.CartItemRepository;
import study.restapi.repository.ItemRepository;
import study.restapi.repository.member.MemberRepository;
import study.restapi.service.dto.CartItemDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartItemService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;

    /**
     * create
     **/

    @Transactional
    public CartItemDto save(CartItemForm form){
        Member member = memberRepository.findById(form.getMemberId()).orElseThrow(() -> new NotCorrespondingException("memberId에 해당하는 member가 존재하지 않습니다."));
        Item item = itemRepository.findById(form.getItemId()).orElseThrow(() -> new NotCorrespondingException("itemId에 해당하는 item이 존재하지 않습니다."));
        CartItem cartItem = CartItem.createCartItem(member, item);
        cartItemRepository.save(cartItem);
        return CartItemDto.createCartItemDto(cartItem);
    }

    /**
     * read
     **/
    public List<CartItemDto> findAll(Long memberId){
        return cartItemRepository.findAllByMemberId(memberId)
                .stream().map(cartItem -> CartItemDto.createCartItemDto(cartItem))
                .collect(Collectors.toList());
    }

    /**
     * update(?)
     **/

    /**
     * delete
     **/
    @Transactional
    public void delete(Long cartItemId){
        cartItemRepository.deleteById(cartItemId);
    }

}
