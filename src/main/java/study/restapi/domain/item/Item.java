package study.restapi.domain.item;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import study.restapi.form.ItemForm;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.SINGLE_TABLE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public abstract class Item {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "item_id", updatable = false)
    private Long id;

    @Column(length = 24, nullable = false)
    private String name;

    @Column(insertable = false, updatable = false)
    private String type;

    @Column(nullable = false)
    private int price;

    /** Item 업데이트 **/
    public void updateBaseItem(String name, int price){
        this.name = name;
        this.price = price;
    }

    /** Book, Movie 업데이트 **/
    public abstract void update(ItemForm form);

}
