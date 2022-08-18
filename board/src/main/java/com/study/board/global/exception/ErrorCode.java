package com.study.board.global.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
	INVALID_REQUEST("", HttpStatus.BAD_REQUEST),
	INTERNAL_ERROR("", HttpStatus.INTERNAL_SERVER_ERROR),
	ENTITY_NOT_FOUND("데이터를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

	private String message;
	private HttpStatus httpStatus;
}
