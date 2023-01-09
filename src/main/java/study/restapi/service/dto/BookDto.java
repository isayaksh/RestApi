package study.restapi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.restapi.domain.item.Book;

import static lombok.AccessLevel.PROTECTED;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class BookDto {
    private String name;
    private int price;

    private String author;
    private String isbn;

}
