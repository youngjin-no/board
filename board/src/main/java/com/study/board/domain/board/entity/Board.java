package com.study.board.domain.board.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
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

	public Board(Long id, String title, String content) {
		super();
	}

}
