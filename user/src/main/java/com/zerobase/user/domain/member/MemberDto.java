package com.zerobase.user.domain.member;

import com.zerobase.user.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String email;

    public static MemberDto from(Member member) {
        return new MemberDto(member.getId(), member.getEmail());
    }
}
