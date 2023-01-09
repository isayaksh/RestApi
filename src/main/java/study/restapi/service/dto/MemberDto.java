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

    public static MemberDto createMemberDto(Long id, String username, String password, int age){
        MemberDto dto = new MemberDto();
        dto.id = id;
        dto.username = username;
        dto.password = password;
        dto.age = age;
        return dto;
    }

    public MemberDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.age = member.getAge();
    }
}
