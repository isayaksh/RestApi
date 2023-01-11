package study.restapi.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemForm {

    private String name;
    private int price;

    // Book
    private String author;
    private String isbn;

    // Movie
    private String director;
    private String actor;

}
