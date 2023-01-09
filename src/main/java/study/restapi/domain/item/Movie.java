package study.restapi.domain.item;

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
@NoArgsConstructor(access = PROTECTED)
public class Movie extends Item{

    @Column(length = 24, nullable = false)
    private String director;

    @Column(length = 24, nullable = false)
    private String actor;

    /** Movie 업데이트 **/
    @Override
    public void update(ItemForm form) {
        this.actor = form.getActor();
        this.director = form.getDirector();
    }
}
