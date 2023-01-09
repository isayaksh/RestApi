package study.restapi.form;

import lombok.Data;

@Data
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
