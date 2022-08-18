package com.study.board.integration.domain.board.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.study.board.domain.board.model.BoardDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.model.BoardDtoAssembler;
import com.study.board.domain.board.service.BoardService;
import com.study.board.global.util.SHA512;
import com.study.board.global.exception.board.BoardException;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
class IntegrationBoardServiceTest {

	@Autowired
	BoardService boardService;
	@Autowired
	EntityManager em;

	@Test
	@DisplayName("게시판 저장 테스트")
	public void saveTest() {
		Board board = getBoard("tester");
		BoardDto dto = BoardDtoAssembler.toBoardDto(board);
		BoardDto savedDto = boardService.saveBoard(dto);

		assertThat(dto.getSubject()).isEqualTo(savedDto.getSubject());
		assertThat(dto.getContents()).isEqualTo(savedDto.getContents());
		assertThat(dto.getWriter()).isEqualTo(savedDto.getWriter());
		assertThat(dto.isDelete()).isEqualTo(savedDto.isDelete());
	}

	@Test
	@DisplayName("게시판 삭제 테스트")
	public void deleteTest() {
		Board board = getBoard("tester");
		em.persist(board);

		boolean result = boardService.deleteBoard(board.getId());

		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("게시판 삭제 실패 테스트")
	public void deleteFailTest() {

		assertThatThrownBy(() -> boardService.deleteBoard(1L)).isInstanceOf(BoardException.class);

	}

	@Test
	@DisplayName("게시판 리스트 테스트")
	public void getBoardList() {

		String[] writers = new String[] {"김영진", "이병희", "김준엽", "홍의표", "노영진"};
		for (int i = 0; i < 50; i++) {
			em.persist(getBoard(writers[i % 5]));
		}

		List<Board> boardListAll = boardService.getBoardListAll();

		assertThat(boardListAll.size()).isEqualTo(50);
	}

	private Board getBoard(String tester) {
		Board board = Board.builder()
			.subject("example")
			.contents("test contents")
			.writer(tester)
			.isDelete(false)
			.password(SHA512.decryption(""))
			.build();
		return board;
	}

}