package com.study.board.util.exception.board;

import org.springframework.http.HttpStatus;

import com.study.board.util.exception.ErrorCode;

public class BoardException extends RuntimeException {
	private ErrorCode errorCode;
	private String message;
	private HttpStatus httpStatus;

	public BoardException(ErrorCode errorCode) {
		this(errorCode, errorCode.getMessage(), errorCode.getHttpStatus());
	}

	public BoardException(ErrorCode errorCode, String customMessage, HttpStatus status) {
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
