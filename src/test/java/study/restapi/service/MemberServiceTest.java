package study.restapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.restapi.exception.NotCorrespondingException;
import study.restapi.form.MemberForm;
import study.restapi.repository.member.MemberSearch;
import study.restapi.service.dto.MemberDto;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired EntityManager em;

    @Test
    public void test_save() throws Exception {
        // given
        String username = "kim";
        String password = "9870";
        int age = 27;

        // when
        MemberDto savedMember = saveMember(username, password, age);

        // then
        assertThat(savedMember.getUsername()).isEqualTo(username);
        assertThat(savedMember.getPassword()).isEqualTo(password);
        assertThat(savedMember.getAge()).isEqualTo(age);
    }

    @Test
    public void test_read() throws Exception {
        // given
        String username = "kim";
        String password = "9870";
        int age = 27;
        MemberDto saveMember = saveMember(username, password, age);
        // when
        MemberDto findMember = memberService.findOne(saveMember.getId());
        // then
        assertThat(findMember.getUsername()).isEqualTo(username);
        assertThat(findMember.getPassword()).isEqualTo(password);
        assertThat(findMember.getAge()).isEqualTo(age);
    }

    @Test
    public void member_findAllByNameAndAge() throws Exception {
        // given
        String username1 = "kim";
        String password1 = "9870";
        int age1 = 27;
        MemberDto saveMember1 = saveMember(username1, password1, age1);

        String username2 = "lee";
        String password2 = "2468";
        int age2 = 22;
        MemberDto saveMember2 = saveMember(username2, password2, age2);
        // when
        Page<MemberDto> findMember1 = memberService.findAllByNameAndAge(new MemberSearch(username1, null), Pageable.unpaged());
        Page<MemberDto> findMember2 = memberService.findAllByNameAndAge(new MemberSearch(username2, null), Pageable.unpaged());
        Page<MemberDto> findMember3 = memberService.findAllByNameAndAge(new MemberSearch(null, age1), Pageable.unpaged());
        Page<MemberDto> findMember4 = memberService.findAllByNameAndAge(new MemberSearch(null, age2), Pageable.unpaged());
        // then

    }
    
    @Test
    public void test_update() throws Exception {
        // given
        String username = "kim";
        String password = "9870";
        int age = 27;
        MemberDto saveMember = saveMember("lee", "1230", 22);
        // when
        MemberDto updatedMember = memberService.update(saveMember.getId(), new MemberForm(username, password, age));
        em.flush();
        em.clear();
        // then
        assertThat(updatedMember.getUsername()).isEqualTo(username);
        assertThat(updatedMember.getPassword()).isEqualTo(password);
        assertThat(updatedMember.getAge()).isEqualTo(age);
    }

    @Test
    public void test_delete() throws Exception {
        // given
        String username = "kim";
        String password = "9870";
        int age = 27;
        MemberDto saveMember = saveMember(username, password, age);
        // when
        memberService.delete(saveMember.getId());
        em.flush();
        em.clear();
        // then
        assertThatThrownBy(() -> memberService.findOne(saveMember.getId()))
                .isInstanceOf(NotCorrespondingException.class);
    }

    public MemberDto saveMember(String username, String password, int age){
        MemberForm form = new MemberForm(username, password, age);
        return memberService.save(form);
    }

}