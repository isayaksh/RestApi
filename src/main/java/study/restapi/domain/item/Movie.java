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
@DiscriminatorValue("MOVIE")
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends Item{

    @Column(length = 24)
    private String director;

    @Column(length = 24)
    private String actor;

    // update Movie
    @Override
    public void update(ItemForm form) {
        this.setItem(form.getName(), form.getPrice());
        this.actor = form.getActor();
        this.director = form.getDirector();
    }

    // create Movie
    public static Movie createMovie(String name, int price, String director, String actor){
        Movie movie = new Movie(director, actor);
        movie.setItem(name, price);
        return movie;
    }

}
