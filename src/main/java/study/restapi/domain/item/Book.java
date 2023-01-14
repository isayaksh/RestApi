package study.restapi.domain.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.restapi.form.ItemForm;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@DiscriminatorValue("BOOK")
@NoArgsConstructor
@AllArgsConstructor
public class Book extends Item{

    @Column(length = 24)
    private String author;

    @Column(length = 24)
    private String isbn;


    // update Book
    @Override
    public void update(ItemForm form) {
        this.setItem(form.getName(), form.getPrice());
        this.author = form.getAuthor();
        this.isbn = form.getIsbn();
    }

    public static Book createBook(String name, int price, String author, String isbn){
        Book book = new Book(author, isbn);
        book.setItem(name,price);
        return book;
    }
}
