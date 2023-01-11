package study.restapi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.restapi.domain.item.Book;
import study.restapi.domain.item.Item;
import study.restapi.domain.item.Movie;
import study.restapi.form.ItemForm;

import static lombok.AccessLevel.PROTECTED;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class ItemDto {
    private Long id;
    private String name;
    private int price;

    private String author;
    private String isbn;

    private String director;
    private String actor;

    public static ItemDto createItemDto(Item item){
        ItemDto dto = new ItemDto();
        dto.id = item.getId();
        dto.name = item.getName();
        dto.price = item.getPrice();

        if(item instanceof Book){
            Book book = (Book) item;
            dto.author = book.getAuthor();
            dto.isbn = book.getIsbn();
        } else if (item instanceof Movie) {
            Movie movie = (Movie) item;
            dto.director = movie.getDirector();
            dto.actor = movie.getActor();
        }
        return dto;
    }
}
