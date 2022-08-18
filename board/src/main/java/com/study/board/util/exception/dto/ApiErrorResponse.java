package com.study.board.util.exception.dto;

public class ApiErrorResponse {
	private String message;

	private ApiErrorResponse() {
	}

	public ApiErrorResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
