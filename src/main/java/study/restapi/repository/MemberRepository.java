package study.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.restapi.domain.member.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {

}
