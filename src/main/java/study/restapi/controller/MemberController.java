package study.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import study.restapi.repository.member.MemberSearch;
import study.restapi.service.dto.MemberDto;
import study.restapi.form.MemberForm;
import study.restapi.service.MemberService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // POST : localhost:8080/members
    @PostMapping
    public MemberDto save(@RequestBody MemberForm form){
        return memberService.save(form);
    }

    // GET : localhost:8080/members
    @GetMapping
    public Page<MemberDto> findAllByNameAndAge(@PageableDefault(size = 8, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable,
                                               @ModelAttribute MemberSearch memberSearch){
        return memberService.findAllByNameAndAge(memberSearch, pageable);
    }

    // GET : localhost:8080/members/memberId
    @GetMapping("/{memberId}")
    public MemberDto findOne(@PathVariable Long memberId){
        return memberService.findOne(memberId);
    }

    // PATCH : localhost:8080/members/memberId
    @PatchMapping("/{memberId}")
    public MemberDto update(@PathVariable Long memberId,
                            @RequestBody MemberForm form){
        return memberService.update(memberId, form);
    }

    // DELETE : localhost:8080/members/memberId
    @DeleteMapping("/{memberId}")
    public void delete(@PathVariable Long memberId){
        memberService.delete(memberId);
    }

}