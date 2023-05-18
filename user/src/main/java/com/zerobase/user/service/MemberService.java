package com.zerobase.user.service;

import com.zerobase.user.domain.entity.Member;
import com.zerobase.user.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Optional<Member> findByIdAndEmail(Long id, String email) {
        return memberRepository.findById(id)
                .stream()
                .filter(member -> member.getEmail().equals(email))
                .findFirst();
    }

    public Optional<Member> findValidMember(String email, String password) {
        return memberRepository.findByEmail(email)
                .stream()
                .filter(member -> member.getPassword().equals(password) && member.isVerify())
                .findFirst();
    }
}
