package com.study.board.domain.board.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.study.board.domain.board.entity.Board;

class JpaBoardRepositoryTest {

	@Autowired BoardRepository boardRepository;

	@Test
	void findAll() {
		List<Board> result = boardRepository.findAll();
		assertThat(result.size()).isEqualTo(100);
	}
}