package study.restapi.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.restapi.domain.member.Member;

import static lombok.AccessLevel.PROTECTED;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private int age;

    public static MemberDto createMemberDto(Member member){
        MemberDto dto = new MemberDto();
        dto.id = member.getId();
        dto.username = member.getUsername();
        dto.password = member.getPassword();
        dto.age = member.getAge();
        return dto;
    }

    public MemberDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.age = member.getAge();
    }
}
