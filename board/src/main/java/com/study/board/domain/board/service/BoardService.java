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
import com.study.board.domain.board.model.BoardDtoForSave;
import com.study.board.domain.board.model.BoardDtoForUpdate;
import com.study.board.domain.board.model.BoardSearchCond;
import com.study.board.domain.board.repository.BoardRepository;
import com.study.board.global.exception.ErrorCode;
import com.study.board.global.exception.board.BoardException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BoardService {
	private final BoardRepository boardRepository;

	@Transactional
	public BoardDto saveBoard(BoardDtoForSave boardDto) {
		Board board = BoardDtoAssembler.boardFromSaveDto(boardDto);
		board.encryptPassword();
		Board save = boardRepository.save(board);
		return BoardDtoAssembler.toBoardDto(save);
	}

	public BoardDto getBoardDetail(Long boardId) {
		Board board = getBoard(boardId);
		return BoardDtoAssembler.toBoardDto(board);
	}

	@Transactional
	public BoardDto editBoard(Long boardId, BoardDtoForUpdate updateDto){
		Board board = getBoard(boardId);
		board.editBoard(updateDto);
		BoardDto boardDto = BoardDtoAssembler.toBoardDto(board);
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
