package study.restapi.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class ItemDto {
    private String name;
    private String type;
    private int price;

    // Book
    private String author;
    private String isbn;

    // Movie
    private String director;
    private String actor;
}
