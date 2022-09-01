package com.study.unit.domain.board.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.study.domain.board.entity.Board;
import com.study.domain.board.model.BoardDto;
import com.study.domain.board.model.BoardDtoAssembler;
import com.study.domain.board.model.BoardDtoForPage;
import com.study.domain.board.model.BoardDtoForSave;
import com.study.domain.board.model.BoardDtoForUpdate;
import com.study.domain.board.model.BoardSearchCond;
import com.study.domain.board.repository.BoardRepository;
import com.study.domain.board.service.BoardService;
import com.study.global.exception.board.BoardException;
import com.study.unit.domain.board.BoardConstantForTest;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class BoardServiceTest extends BoardConstantForTest {

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
		BoardDtoForSave boardDto = BoardDtoAssembler.toBoardSaveDto(board);
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
		Page<BoardDtoForPage> pageBoard = getBoardsWithPage();
		given(boardRepository.getBoardListWithPage(any(BoardSearchCond.class), any(Pageable.class))).willReturn(
			pageBoard);

		Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
		Page<BoardDtoForPage> result = boardService.getBoardListWithPage(new BoardSearchCond(), pageable);

		assertThat(result.getContent().get(0)).isEqualTo(pageBoard.getContent().get(0));
		verify(boardRepository, times(1))
			.getBoardListWithPage(any(BoardSearchCond.class), any(Pageable.class));
	}

	@DisplayName("게시판 수정 서비스")
	@Test
	void BoardUpdateTest() {
		Board board = getBoard();
		BoardDtoForUpdate boardDto = getBoardDtoForUpdate(PASSWORD);

		given(boardRepository.findById(anyLong()))
			.willReturn(Optional.of(board));

		Long result = boardService.editBoard(1L, boardDto);

		assertThat(1L).isEqualTo(result);
		verify(boardRepository, times(1)).findById(anyLong());
	}

	@DisplayName("게시판 수정 실패 서비스")
	@Test
	void BoardUpdateExceptionTest() {
		Board board = getBoard();
		BoardDtoForUpdate boardDto = getBoardDtoForUpdate("");

		given(boardRepository.findById(anyLong()))
			.willReturn(Optional.of(board));

		assertThatThrownBy(() -> boardService.editBoard(1L, boardDto)).isInstanceOf(BoardException.class);
		verify(boardRepository, times(1)).findById(anyLong());
	}

	private BoardDtoForUpdate getBoardDtoForUpdate(String password) {
		return BoardDtoForUpdate.builder()
			.subject(SUBJECT)
			.contents(CONTENTS)
			.writer(WRITER)
			.password(password)
			.build();
	}

	private Board getBoard() {
		Board board = Board.builder()
			.id(1L)
			.subject(SUBJECT)
			.contents(CONTENTS)
			.writer(WRITER)
			.isDelete(false)
			.password(PASSWORD)
			.build();
		return board;
	}

	private Page<BoardDtoForPage> getBoardsWithPage() {
		BoardDtoForPage board = new BoardDtoForPage(1L, SUBJECT, CONTENTS, WRITER, false, LocalDateTime.now(),
			LocalDateTime.now());
		List<BoardDtoForPage> boards = new ArrayList<>();
		boards.add(board);
		return new PageImpl<>(boards);
	}
}
