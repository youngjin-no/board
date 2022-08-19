package com.study.board.unit.domain.board.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.model.BoardDto;
import com.study.board.domain.board.model.BoardDtoAssembler;
import com.study.board.domain.board.model.BoardSaveDto;
import com.study.board.domain.board.repository.BoardRepository;
import com.study.board.domain.board.service.BoardService;
import com.study.board.global.util.SHA512;
import com.study.board.global.exception.board.BoardException;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

	@InjectMocks
	private BoardService boardService;

	@Mock
	private BoardRepository boardRepository;

	@DisplayName("mock 객체 생성 확인")
	@Test
	void mockito_test(){
		assertThat(boardService).isNotNull();
	}


	@DisplayName("게시판 등록 서비스")
	@Test
	void saveBoard_Success() {
		Board board = getBoard();
		BoardSaveDto boardDto = BoardDtoAssembler.toBoardSaveDto(board);

		given(boardRepository.save(any(Board.class)))
			.willReturn(board);

		BoardDto savedBoardDto = boardService.saveBoard(boardDto);

		assertThat(savedBoardDto.getId()).isNotNull();
		verify(boardRepository, times(1))
			.save(any(Board.class));
	}

	@DisplayName("게시판 삭제 서비스")
	@Test
	void deleteBoard_Success() {
		Board board = getBoard();

		given(boardRepository.findById(anyLong()))
			.willReturn(Optional.of(board));

		boolean result = boardService.deleteBoard(1L);

		assertThat(result).isTrue();
		verify(boardRepository, times(1))
			.findById(anyLong());
	}

	@DisplayName("게시판 삭제 서비스 실패 - 예외 발생")
	@Test
	void deleteBoard_Exception() {

		assertThatThrownBy(()->boardService.deleteBoard(2L))
			.isInstanceOf(BoardException.class);

	}

	private Board getBoard() {
		Board board = Board.builder()
			.id(1L)
			.subject("example")
			.contents("test contents")
			.writer("tester")
			.isDelete(false)
			.password(SHA512.decryption(""))
			.build();
		return board;
	}
}
