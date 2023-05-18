package com.zerobase.user.controller;

import com.zerobase.domain.common.UserVo;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import com.zerobase.user.exception.ErrorCode;
import com.zerobase.user.exception.MemberException;
import com.zerobase.user.domain.member.MemberDto;
import com.zerobase.user.domain.entity.Member;
import com.zerobase.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final JwtAuthenticationProvider provider;
    private final MemberService memberService;

    @GetMapping("/getInfo")
    public ResponseEntity<MemberDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
        UserVo vo = provider.getUserVo(token);
        Member m = memberService.findByIdAndEmail(vo.getId(), vo.getEmail())
                .orElseThrow(() -> new MemberException(ErrorCode.NOT_FOUND_USER));
        return ResponseEntity.ok(MemberDto.from(m));
    }
}
