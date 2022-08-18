package com.study.board.util.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.study.board.util.exception.board.BoardException;
import com.study.board.util.exception.dto.ApiErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final String LOG_FORMAT = "Class : {}, Code : {}, Message : {}";

	@ExceptionHandler(BoardException.class)
	public ResponseEntity<ApiErrorResponse> applicationException(BoardException e) {
		log.warn(
			LOG_FORMAT,
			e.getClass().getSimpleName(),
			e.getErrorCode(),
			e.getMessage()
		);
		return ResponseEntity
			.status(e.getHttpStatus())
			.body(new ApiErrorResponse(e.getMessage()));
	}
}
