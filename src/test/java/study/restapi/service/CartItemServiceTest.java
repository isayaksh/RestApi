package study.restapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.restapi.domain.item.Book;
import study.restapi.domain.item.Movie;
import study.restapi.domain.member.Member;
import study.restapi.form.CartItemForm;
import study.restapi.repository.ItemRepository;
import study.restapi.repository.member.MemberRepository;
import study.restapi.service.dto.CartItemDto;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CartItemServiceTest {

    @Autowired CartItemService cartItemService;
    @Autowired MemberRepository memberRepository;
    @Autowired ItemRepository itemRepository;

    @Test
    public void cart_item_save() throws Exception {
        // given
        Member member = createMember();
        Book book = createBook();
        CartItemForm form = new CartItemForm(member.getId(), book.getId());

        // when
        CartItemDto savedCartItem = cartItemService.save(form);

        // then
        assertThat(savedCartItem.getMemberId()).isEqualTo(member.getId());
        assertThat(savedCartItem.getItemId()).isEqualTo(book.getId());
    }

    @Test
    public void cart_item_findAll() throws Exception {
        // given
        Member member = createMember();
        Book book = createBook();
        Movie movie = createMovie();

        CartItemForm form1 = new CartItemForm(member.getId(), book.getId());
        cartItemService.save(form1);
        CartItemForm form2 = new CartItemForm(member.getId(), movie.getId());
        cartItemService.save(form2);
        // when
        List<CartItemDto> findCarItems = cartItemService.findAll(member.getId());
        // then
        assertThat(findCarItems.size()).isEqualTo(2);
    }

    @Test
    public void cart_item_delete() throws Exception {
        // given
        Member member = createMember();
        Book book = createBook();
        CartItemForm form1 = new CartItemForm(member.getId(), book.getId());
        CartItemDto savedCartItem = cartItemService.save(form1);
        // when
        cartItemService.delete(savedCartItem.getItemId());
        // then
        List<CartItemDto> findCartItems = cartItemService.findAll(member.getId());
        assertThat(findCartItems.size()).isEqualTo(0);
    }

    private Book createBook() {
        Book book = Book.createBook("jpa", 33000, "author", "isbn");
        itemRepository.save(book);
        return book;
    }

    private Movie createMovie() {
        Movie movie = Movie.createMovie("about time", 33000, "director", "actor");
        itemRepository.save(movie);
        return movie;
    }

    private Member createMember() {
        Member member = Member.createMember("user", "password", 27);
        memberRepository.save(member);
        return member;
    }
}