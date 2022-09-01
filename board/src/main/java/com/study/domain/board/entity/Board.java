package com.study.domain.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.util.StringUtils;

import com.study.domain.board.model.BoardDtoForUpdate;
import com.study.global.util.SHA512;

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

	public void editBoard(BoardDtoForUpdate boardDto) {
		if (StringUtils.hasText(boardDto.getSubject()))
			subject = boardDto.getSubject();
		if (StringUtils.hasText(boardDto.getContents()))
			contents = boardDto.getContents();
		if (StringUtils.hasText(boardDto.getWriter()))
			writer = boardDto.getWriter();
	}

	public void encryptPassword() {
		password = SHA512.decryption(password);
	}
	public boolean isValidPassword(String password) {
		return this.password.equals(password);
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
