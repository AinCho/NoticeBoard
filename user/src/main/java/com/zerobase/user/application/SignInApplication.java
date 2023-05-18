package com.zerobase.user.application;

import com.zerobase.domain.common.UserType;
import com.zerobase.user.domain.SignInForm;
import com.zerobase.user.exception.ErrorCode;
import com.zerobase.user.exception.MemberException;
import com.zerobase.user.domain.entity.Member;
import com.zerobase.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.zerobase.domain.config.JwtAuthenticationProvider;

@Service
@RequiredArgsConstructor
public class SignInApplication {
    private final MemberService memberService;
    private final JwtAuthenticationProvider provider;

    public String memberLoginToken(SignInForm form) {
        Member m = memberService.findValidMember(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new MemberException(ErrorCode.LOGIN_CHECK_FAIL));

        return provider.createToken(m.getEmail(), m.getId(), UserType.MEMBER);
    }
}
