package com.study.domain.board.model;

import org.hibernate.validator.constraints.Length;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardDtoForUpdate {
	private String subject;
	private String contents;
	@Length(max = 20)
	private String writer;
	private String password;
}
