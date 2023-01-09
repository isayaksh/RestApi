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
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Book extends Item{

    @Column(length = 24, nullable = false)
    private String author;

    @Column(length = 24, nullable = false)
    private String isbn;

    /** Book 업데이트 **/
    @Override
    public void update(ItemForm form) {
        this.author = form.getAuthor();
        this.isbn = form.getIsbn();
    }
}
