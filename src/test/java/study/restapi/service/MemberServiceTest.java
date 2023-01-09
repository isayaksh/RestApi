package study.restapi.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.restapi.exception.NotCorrespondingException;
import study.restapi.form.MemberForm;
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