package com.study.board.unit.domain.board.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.study.board.domain.board.model.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.repository.BoardRepository;
import com.study.board.domain.board.service.BoardService;
import com.study.board.global.util.SHA512;
import com.study.board.global.exception.board.BoardException;

import org.springframework.data.domain.*;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

	@InjectMocks
	private BoardService boardService;

	@Mock
	private BoardRepository boardRepository;

	@DisplayName("mock 객체 생성 확인")
	@Test
	void mockito_test() {
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

		assertThatThrownBy(() -> boardService.deleteBoard(2L))
			.isInstanceOf(BoardException.class);

	}

	@DisplayName("게시판 조회 서비스")
	@Test
	void BoardListTest() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
		BoardSearchCond boardSearchCond = new BoardSearchCond();
		BoardDtoForPage board = new BoardDtoForPage(1L, "example", "test contens", "tester", false, LocalDateTime.now(),
			LocalDateTime.now());
		List<BoardDtoForPage> boards = new ArrayList<>();
		boards.add(board);
		Page<BoardDtoForPage> pageBoard = new PageImpl<>(boards);
		given(boardRepository.getBoardListWithPage(any(BoardSearchCond.class), any(Pageable.class))).willReturn(
			pageBoard);

		Page<BoardDtoForPage> result = boardService.getBoardListWithPage(boardSearchCond, pageable);

		assertThat(result.getContent().get(0)).isEqualTo(boards.get(0));
		verify(boardRepository, times(1))
			.getBoardListWithPage(any(BoardSearchCond.class), any(Pageable.class));
	}

	@DisplayName("게시판 수정 서비스")
	@Test
	void BoardUpdateTest() {
		Board board = getBoard();
		BoardDto boardDto = BoardDtoAssembler.toBoardDto(board);
		given(boardRepository.findById(anyLong()))
			.willReturn(Optional.of(board));

		BoardDto result = boardService.editBoard(1L, boardDto);

		assertThat(result).isEqualTo(boardDto);
		verify(boardRepository, times(1)).findById(anyLong());
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
