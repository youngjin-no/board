package com.study.board.domain.board.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BoardSaveDto {
	@NotBlank(message = "{notblank.subject}")
	private String subject;
	@NotBlank(message = "{notblank.contents}")
	private String contents;
	@Range(min = 1, max = 20, message = "{range.writer}")
	private String writer;
}
