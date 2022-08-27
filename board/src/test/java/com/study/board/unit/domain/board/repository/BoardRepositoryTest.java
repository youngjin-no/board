package com.study.board.unit.domain.board.repository;

import java.util.NoSuchElementException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.repository.BoardRepository;
import com.study.board.global.util.SHA512;

@DataJpaTest
public class BoardRepositoryTest {

	public static final String SUBJECT = "example";
	public static final String CONTENTS = "test contents";
	public static final String WRITER = "tester";
	public static final String PASSWORD = SHA512.decryption("");
	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private TestEntityManager tem;

	@DisplayName("게시판을 저장한다.")
	@Test
	void saveBoard_Success() {
		Board savedBoard = boardRepository.save(getBoard());
		flushAndClear();

		Board actualBoard = boardRepository.findById(savedBoard.getId())
			.orElseThrow(NoSuchElementException::new);

		Assertions.assertThat(actualBoard.getId()).isEqualTo(savedBoard.getId());
	}

	@DisplayName("게시판을 삭제한다.")
	@Test
	void deleteBoard_Success() {
		Board savedBoard = boardRepository.save(getBoard());
		savedBoard.deleteBoard();
		flushAndClear();

		Board actualBoard = boardRepository.findById(savedBoard.getId())
			.orElseThrow(NoSuchElementException::new);



		Assertions.assertThat(actualBoard.isDelete()).isEqualTo(savedBoard.isDelete());
	}

	private void flushAndClear() {
		tem.flush();
		tem.clear();
	}

	private Board getBoard() {
		Board board = Board.builder()
			.subject(SUBJECT)
			.contents(CONTENTS)
			.writer(WRITER)
			.isDelete(false)
			.password(PASSWORD)
			.build();
		return board;
	}
}
