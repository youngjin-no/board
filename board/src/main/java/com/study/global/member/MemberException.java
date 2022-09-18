package com.study.global.member;

import com.study.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class MemberException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;
    private HttpStatus httpStatus;

    public MemberException(ErrorCode errorCode){
        this(errorCode, errorCode.getMessage(), errorCode.getHttpStatus());
    }

    public MemberException(ErrorCode errorCode, String customMessage, HttpStatus status) {
        super(customMessage);
        this.errorCode = errorCode;
        this.message = customMessage;
        this.httpStatus = status;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
