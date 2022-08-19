package com.study.board.domain.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.model.BoardDto;
import com.study.board.domain.board.model.BoardDtoAssembler;
import com.study.board.domain.board.model.BoardDtoForPage;
import com.study.board.domain.board.model.BoardSaveDto;
import com.study.board.domain.board.model.BoardSearchCond;
import com.study.board.domain.board.repository.BoardRepository;
import com.study.board.global.exception.ErrorCode;
import com.study.board.global.exception.board.BoardException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
	private final BoardRepository boardRepository;

	@Transactional
	public BoardDto saveBoard(BoardSaveDto boardDto) {
		Board saved = boardRepository.save(BoardDtoAssembler.boardFromSaveDto(boardDto));
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

	public Page<BoardDtoForPage> getBoardListWithPage(BoardSearchCond cond, Pageable pageable) {
		return boardRepository.getBoardListWithPage(cond, pageable);
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
