package com.zerobase.user.service;

import com.zerobase.user.domain.SignUpForm;
import com.zerobase.user.exception.ErrorCode;
import com.zerobase.user.exception.MemberException;
import com.zerobase.user.domain.entity.Member;
import com.zerobase.user.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpMemberService {
    private final MemberRepository memberRepository;

    public Member signUp(SignUpForm form) {
        return memberRepository.save(Member.from(form));
    }

    public boolean isEmailExist(String email) {
        return memberRepository.findByEmail(email.toLowerCase(Locale.ROOT)).isPresent();
    }

    @Transactional
    public void verifyEmail(String email, String code) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(ErrorCode.NOT_FOUND_USER));
        if (member.isVerify()) {
            throw new MemberException(ErrorCode.ALREADY_VERIFY);
        } else if (member.getVerificationCode().equals(code)) {
            throw new MemberException(ErrorCode.WRONG_VERIFICATION);
        } else if (member.getVerifyExpiredAt().isBefore(LocalDateTime.now())) {
            throw new MemberException(ErrorCode.EXPIRE_CODE);
        }
        member.setVerify(true);
    }

    @Transactional
    public LocalDateTime changeMemberValidateEmail(Long memberId, String verificationCode) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.setVerificationCode(verificationCode);
            member.setVerifyExpiredAt(LocalDateTime.now().plusDays(1));
            return member.getVerifyExpiredAt();
        }
        throw new MemberException(ErrorCode.NOT_FOUND_USER);
    }
}
