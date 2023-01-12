package study.restapi.repository.member;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import study.restapi.domain.member.Member;
import study.restapi.service.dto.MemberDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static study.restapi.domain.member.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryJpql{

    private final EntityManager em;

    @Override
    public Page<Member> findAll(MemberSearch memberSearch, Pageable pageable) {
        JPAQueryFactory query = new JPAQueryFactory(em);

        List<Member> members = query
                .select(member)
                .from(member)
                .where(ageEq(memberSearch.getAge()), nameContains(memberSearch.getUsername()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(member.createdDate.desc())
                .fetch();

        members.stream().map(MemberDto::new)
                .collect(Collectors.toList());

        Long totalCount = query
                .select(member.count())
                .from(member)
                .where(ageEq(memberSearch.getAge()), nameContains(memberSearch.getUsername()))
                .fetchOne();

        return new PageImpl<>(members, pageable, totalCount);
    }

    private BooleanExpression nameContains(String itemName) {
        if(!StringUtils.hasText(itemName)){
            return null;
        }
        return member.username.contains(itemName);
    }

    private BooleanExpression ageEq(Integer age) {
        if(age == null){
            return null;
        }
        return member.age.eq(age);
    }
}
