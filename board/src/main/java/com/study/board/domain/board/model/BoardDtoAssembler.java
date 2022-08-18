package com.study.board.domain.board.model;

import com.study.board.domain.board.entity.Board;

public class BoardDtoAssembler {

	public static BoardDto toBoardDto(Board board) {
		return BoardDto.builder()
			.id(board.getId())
			.subject(board.getSubject())
			.contents(board.getContents())
			.writer(board.getWriter())
			.isDelete(board.isDelete())
			.createdDate(board.getCreatedDate())
			.lastModifiedDate(board.getLastModifiedDate())
			.build();
	}

	public static Board board(BoardDto boardDto) {
		return Board.builder()
			.subject(boardDto.getSubject())
			.contents(boardDto.getContents())
			.writer(boardDto.getWriter())
			.isDelete(boardDto.isDelete())
			.build();
	}
}
