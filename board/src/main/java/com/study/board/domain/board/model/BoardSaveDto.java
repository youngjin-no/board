package com.study.board.domain.board.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.study.board.global.util.SHA512;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class BoardSaveDto {
	@NotBlank(message = "{notblank.subject}")
	private String subject;
	@NotBlank(message = "{notblank.contents}")
	private String contents;
	@Length(min = 1, max = 20, message = "{range.writer}")
	private String writer;
	@NotBlank(message = "{notblank.password}")
	private String password;
}
