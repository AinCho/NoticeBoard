package com.zerobase.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    ALREADY_REGISTER_USER(HttpStatus.BAD_REQUEST, "이미 존재하는 유저입니다."),
    WRONG_VERIFICATION(HttpStatus.BAD_REQUEST, "잘못된 인증 시도입니다."),
    EXPIRE_CODE(HttpStatus.BAD_REQUEST, "인증 시간이 만료되었습니다."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "일치하는 유저가 없습니다."),
    LOGIN_CHECK_FAIL(HttpStatus.BAD_REQUEST, "이메일 및 비밀번호를 확인해주세요."),
    ALREADY_VERIFY(HttpStatus.BAD_REQUEST, "이미 인증이 완료되었습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
