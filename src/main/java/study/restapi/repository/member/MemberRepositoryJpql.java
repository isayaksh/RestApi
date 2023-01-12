package study.restapi.repository.member;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.restapi.domain.member.Member;

public interface MemberRepositoryJpql {
    Page<Member> findAll(MemberSearch memberSearch, Pageable pageable);
}
