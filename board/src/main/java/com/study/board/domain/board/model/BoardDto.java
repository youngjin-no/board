package com.study.board.domain.board.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.querydsl.core.annotations.QueryProjection;
import com.study.board.domain.board.entity.Board;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardDto {
	private Long id;
	private String subject;
	private String contents;
	@Length(max = 20)
	private String writer;
	private boolean isDelete;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BoardDto boardDto = (BoardDto)o;
		return isDelete == boardDto.isDelete && Objects.equals(id, boardDto.id) && Objects.equals(
			subject, boardDto.subject) && Objects.equals(contents, boardDto.contents) && Objects.equals(
			writer, boardDto.writer) && Objects.equals(createdDate, boardDto.createdDate)
			&& Objects.equals(lastModifiedDate, boardDto.lastModifiedDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
