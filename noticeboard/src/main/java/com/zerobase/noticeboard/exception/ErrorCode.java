package com.zerobase.noticeboard.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND_POST(HttpStatus.BAD_REQUEST, "게시글을 찾을 수 없습니다."),
    NOT_FOUND_ID(HttpStatus.BAD_REQUEST, "Id로 게시글을 찾을 수 없습니다."),
    NOT_FOUND_POSTBYCREATEDAT(HttpStatus.BAD_REQUEST, "해당 날짜로 게시글을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
