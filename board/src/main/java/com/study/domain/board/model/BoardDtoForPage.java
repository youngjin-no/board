package com.study.domain.board.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class BoardDtoForPage {
	private Long id;
	private String subject;
	private String contents;
	@Max(value = 20)
	private String writer;
	private boolean isDelete;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;

	@QueryProjection
	public BoardDtoForPage(Long id, String subject, String contents, String writer, boolean isDelete,
		LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
		this.id = id;
		this.subject = subject;
		this.contents = contents;
		this.writer = writer;
		this.isDelete = isDelete;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
	}
}
