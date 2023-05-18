package com.zerobase.user.controller;

import com.zerobase.user.exception.ErrorCode;
import com.zerobase.user.exception.MemberException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler({MemberException.class})
    public ResponseEntity<ExceptionResponse> memberRequestException(final MemberException m) {
        log.warn("api Exception : {}", m.getErrorCode());
        return ResponseEntity.badRequest().body(new ExceptionResponse(m.getMessage(), m.getErrorCode()));
    }

    @Getter
    @ToString
    @AllArgsConstructor
    public static class ExceptionResponse {
        private String message;
        private ErrorCode errorCode;
    }
}
