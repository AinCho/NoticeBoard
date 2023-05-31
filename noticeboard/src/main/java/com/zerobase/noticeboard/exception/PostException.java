package com.zerobase.noticeboard.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;


@Getter
public class PostException extends RuntimeException{
    private final ErrorCode_b errorCode_b;
    private final int status;
    private static final ObjectMapper mapper = new ObjectMapper();

    public PostException(ErrorCode_b errorCode_b) {
        super(errorCode_b.getDetail());
        this.errorCode_b = errorCode_b;
        this.status = errorCode_b.getHttpStatus().value();
    }
}
