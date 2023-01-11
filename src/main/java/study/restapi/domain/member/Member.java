package study.restapi.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.restapi.domain.BaseEntity;
import study.restapi.domain.cartitem.CartItem;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long id;

    @Column(length = 24, nullable = false)
    private String username;

    @Column(length = 24, nullable = false)
    private String password;

    @Column(nullable = false)
    private int age;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<CartItem> cartItems = new ArrayList<>();

    /** 생성자 **/
    public static Member createMember(String username, String password, int age){
        Member member = new Member();
        member.username = username;
        member.password = password;
        member.age = age;
        return member;
    }

    /** update **/
    public void update(String username, String password, int age){
        this.username = username;
        this.password = password;
        this.age = age;
    }
}
