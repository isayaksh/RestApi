package study.restapi.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.restapi.exception.NotCorrespondingException;
import study.restapi.form.ItemForm;
import study.restapi.service.dto.ItemDto;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired ItemService itemService;

    @Test
    public void item_save() throws Exception {
        // given
        String bookType = "book";
        String movieType = "movie";
        ItemForm bookForm = new ItemForm("book", 4000, "author", "isbn", null, null);
        ItemForm movieForm = new ItemForm("movie", 3333, null, null, "director", "actor");

        // when
        ItemDto bookDto = itemService.save(bookForm, bookType);
        ItemDto movieDto = itemService.save(movieForm, movieType);

        // then
        assertThat(bookDto.getName()).isEqualTo("book");
        assertThat(bookDto.getPrice()).isEqualTo(4000);
        assertThat(bookDto.getAuthor()).isEqualTo("author");
        assertThat(bookDto.getIsbn()).isEqualTo("isbn");

        assertThat(movieDto.getName()).isEqualTo("movie");
        assertThat(movieDto.getPrice()).isEqualTo(3333);
        assertThat(movieDto.getDirector()).isEqualTo("director");
        assertThat(movieDto.getActor()).isEqualTo("actor");
    }

    @Test
    public void item_findAll() throws Exception {
        // given
        ItemDto movieDto = createMovie();
        ItemDto bookDto = createBook();
        // when
        List<ItemDto> items = itemService.findAll();
        // then
        assertThat(items.size()).isEqualTo(2);
    }

    @Test
    public void item_findOne() throws Exception {
        // given
        ItemDto bookDto = createBook();
        // when
        ItemDto findItem = itemService.findOne(bookDto.getId());
        assertThat(findItem.getName()).isEqualTo("book");
        assertThat(findItem.getPrice()).isEqualTo(4000);
        assertThat(findItem.getAuthor()).isEqualTo("author");
        assertThat(findItem.getIsbn()).isEqualTo("isbn");
        // then
    }

    @Test
    public void item_update() throws Exception {
        // given
        ItemDto bootDto = createBook();
        // when
        ItemForm form = new ItemForm("jpa book", 3333, "kyh", "isbn", null, null);
        ItemDto updatedBookDto = itemService.update(bootDto.getId(), form);
        // then
        assertThat(updatedBookDto.getName()).isEqualTo("jpa book");
        assertThat(updatedBookDto.getPrice()).isEqualTo(3333);
        assertThat(updatedBookDto.getAuthor()).isEqualTo("kyh");
        assertThat(updatedBookDto.getIsbn()).isEqualTo("isbn");
    }

    @Test
    public void item_delete() throws Exception {
        // given
        ItemDto bookDto = createBook();
        // when
        itemService.delete(bookDto.getId());
        // then


        assertThatThrownBy(() -> itemService.findOne(bookDto.getId()))
                .isInstanceOf(NotCorrespondingException.class);
    }

    private ItemDto createBook() {
        String bookType = "book";
        ItemForm bookForm = new ItemForm("book", 4000, "author", "isbn", null, null);
        return itemService.save(bookForm, bookType);
    }

    private ItemDto createMovie() {
        String movieType = "movie";
        ItemForm movieForm = new ItemForm("movie", 3333, null, null, "director", "actor");
        return itemService.save(movieForm, movieType);
    }
}