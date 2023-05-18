package com.zerobase.user.exception;

import com.zerobase.user.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MemberException extends RuntimeException{
    private final ErrorCode errorCode;

    public MemberException(ErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;

    }
}
