package com.study.board.domain.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.board.domain.board.model.BoardDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.model.BoardDtoAssembler;
import com.study.board.domain.board.repository.BoardRepository;
import com.study.board.global.exception.board.BoardException;
import com.study.board.global.exception.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BoardService {
	private final BoardRepository boardRepository;

	@Transactional
	public BoardDto saveBoard(BoardDto boardDto) {
		Board saved = boardRepository.save(BoardDtoAssembler.board(boardDto));
		return BoardDtoAssembler.toBoardDto(saved);
	}

	public BoardDto getBoardDetail(Long boardId) {
		Board board = getBoard(boardId);
		return BoardDtoAssembler.toBoardDto(board);
	}

	@Transactional
	public BoardDto editBoard(Long boardId, BoardDto boardDto){
		Board board = getBoard(boardId);
		board.editBoard(boardDto);
		return boardDto;
	}
	@Transactional
	public boolean deleteBoard(Long boardId) {
		Board board = getBoard(boardId);
		board.deleteBoard();
		return true;
	}

	public List<Board> getBoardListAll() {
		return boardRepository.findAll();
	}

	private Board getBoard(Long boardId) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new BoardException(ErrorCode.ENTITY_NOT_FOUND));
		return board;
	}



}
