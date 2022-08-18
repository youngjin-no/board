package com.study.board.domain.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.util.StringUtils;

import com.study.board.domain.board.model.BoardDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Board extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto created ID
	private Long id;
	private String subject;
	@Lob
	private String contents;
	@Column(length = 20)
	private String writer;
	@Column(name = "DELETE_YN", length = 1)
	private boolean isDelete;
	private String password;

	public void editBoard(BoardDto boardDto) {
		if (StringUtils.hasText(boardDto.getSubject()))
			subject = boardDto.getSubject();
		if (StringUtils.hasText(boardDto.getContents()))
			subject = boardDto.getContents();
		if (StringUtils.hasText(boardDto.getWriter()))
			subject = boardDto.getWriter();
	}

	public void changePassword(String password) {
		this.password = password;
	}

	public void deleteBoard() {
		isDelete = true;
	}

	public void recoverBoard() {
		isDelete = false;
	}
}
