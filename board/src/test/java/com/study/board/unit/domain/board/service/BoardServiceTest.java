package com.study.board.unit.domain.board.service;

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

import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.model.BoardDto;
import com.study.board.domain.board.model.BoardDtoAssembler;
import com.study.board.domain.board.model.BoardDtoForPage;
import com.study.board.domain.board.model.BoardDtoForSave;
import com.study.board.domain.board.model.BoardDtoForUpdate;
import com.study.board.domain.board.model.BoardSearchCond;
import com.study.board.domain.board.repository.BoardRepository;
import com.study.board.domain.board.service.BoardService;
import com.study.board.global.exception.board.BoardException;
import com.study.board.global.util.SHA512;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class BoardServiceTest {

	public static final String SUBJECT = "example";
	public static final String CONTENTS = "test contents";
	public static final String WRITER = "tester";
	public static final String PASSWORD = SHA512.decryption("");

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
		log.info("savedBoardDto={}", savedBoardDto);
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
		BoardDtoForPage board = new BoardDtoForPage(1L, SUBJECT, CONTENTS, WRITER, false, LocalDateTime.now(),
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
		BoardDtoForUpdate boardDto = BoardDtoForUpdate.builder()
			.subject(SUBJECT)
			.contents(CONTENTS)
			.writer(WRITER)
			.build();

		given(boardRepository.findById(anyLong()))
			.willReturn(Optional.of(board));

		BoardDto result = boardService.editBoard(1L, boardDto);

		assertThat(result.getSubject()).isEqualTo(boardDto.getSubject());
		assertThat(result.getContents()).isEqualTo(boardDto.getContents());
		assertThat(result.getWriter()).isEqualTo(boardDto.getWriter());
		verify(boardRepository, times(1)).findById(anyLong());
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
}
