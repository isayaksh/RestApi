package study.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.restapi.service.dto.MemberDto;
import study.restapi.form.MemberForm;
import study.restapi.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // POST : localhost:8080
    @PostMapping
    public MemberDto save(@RequestBody MemberForm form){
        return memberService.save(form);
    }

    // GET : localhost:8080
    @GetMapping
    public List<MemberDto> findAll(){
        return memberService.findAll();
    }

    // GET : localhost:8080/memberId
    @GetMapping("/{memberId}")
    public MemberDto findOne(@PathVariable Long memberId){
        return memberService.findOne(memberId);
    }

    // PATCH : localhost:8080/memberId
    @PatchMapping("/{memberId}")
    public MemberDto update(@PathVariable Long memberId,
                            @RequestBody MemberForm form){
        return memberService.update(memberId, form);
    }

    // DELETE : localhost:8080/memberId
    @DeleteMapping("/{memberId}")
    public void delete(@PathVariable Long memberId){
        memberService.delete(memberId);
    }

}
