package study.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.restapi.domain.member.Member;
import study.restapi.service.dto.MemberDto;
import study.restapi.exception.NotCorrespondingException;
import study.restapi.form.MemberForm;
import study.restapi.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * create
     **/
    @Transactional
    public MemberDto save(MemberForm form){
        Member member = Member.createMember(form.getUsername(), form.getPassword(), form.getAge());
        memberRepository.save(member);
        return MemberDto.createMemberDto(member);
    }

    /**
     * read
     **/
    public List<MemberDto> findAll(){
        return memberRepository.findAll()
                .stream().map(MemberDto::new)
                .collect(Collectors.toList());
    }

    public MemberDto findOne(Long memberId){
        Member member = findById(memberId);
        return MemberDto.createMemberDto(member);
    }

    /**
     * update
     **/
    @Transactional
    public MemberDto update(Long memberId, MemberForm form){
        Member member = findById(memberId);
        member.update(form.getUsername(), form.getPassword(), form.getAge());
        return MemberDto.createMemberDto(member);
    }

    /**
     * delete
     **/
    @Transactional
    public void delete(Long memberId){
        memberRepository.deleteById(memberId);
    }

    // find entity
    private Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new NotCorrespondingException("memberId에 해당하는 Entity가 존재하지 않습니다."));
    }

}
