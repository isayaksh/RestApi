package study.restapi.domain.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.restapi.domain.BaseEntity;
import study.restapi.form.ItemForm;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.SINGLE_TABLE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public abstract class Item extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "item_id", updatable = false)
    private Long id;

    @Column(length = 24, nullable = false)
    private String name;

    @Column(insertable = false, updatable = false)
    private String type;

    @Column(nullable = false)
    private int price;

    // Item update
    public void setItem(String name, int price){
        this.name = name;
        this.price = price;
    }

    // Book, Movie update
    public abstract void update(ItemForm form);

}
