package com.study.board.global.exception;

import static java.util.Objects.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.study.board.global.exception.board.BoardException;
import com.study.board.global.exception.dto.ApiErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final String LOG_FORMAT = "Class : {}, Code : {}, Message : {}";

	/**
	 *  javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
	 *  HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
	 *  주로 @RequestBody, @RequestPart 어노테이션에서 발생
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> methodArgumentNotValidException(
		MethodArgumentNotValidException e
	) {
		String errorCode = requireNonNull(e.getFieldError())
			.getDefaultMessage();
		ApiErrorResponse exceptionResponse = new ApiErrorResponse(errorCode);
		log.warn(LOG_FORMAT, e.getClass().getSimpleName(), errorCode, "@Valid");
		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST.value())
			.body(exceptionResponse);
	}

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
