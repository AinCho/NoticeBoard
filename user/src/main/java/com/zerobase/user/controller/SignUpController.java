package com.zerobase.user.controller;

import com.zerobase.user.application.SignUpApplication;
import com.zerobase.user.domain.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpApplication signUpApplication;

    @PostMapping("/member")
    public ResponseEntity<String> memberSignUp(@RequestBody SignUpForm form) {
        return ResponseEntity.ok(signUpApplication.memberSignUp(form));
    }

    @GetMapping("/member/verify")
    public ResponseEntity<String> verifyMember(String email, String code) {
        signUpApplication.memberVerify(email, code);
        return ResponseEntity.ok("인증이 완료되었습니다.");
    }
}
