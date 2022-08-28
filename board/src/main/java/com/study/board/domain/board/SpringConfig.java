package com.study.board.domain.board;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.study.board.domain.board.repository.BoardRepository;
import com.study.board.domain.board.repository.JpaBoardRepository;
import com.study.board.domain.board.service.BoardService;

@Configuration
public class SpringConfig {
	private final DataSource dataSource;
	private final EntityManager em;

	public SpringConfig(DataSource dataSource, EntityManager em){
		this.dataSource = dataSource;
		this.em = em;
	}

	@Bean
	public BoardService boardService(){
		return new BoardService(boardRepository());
	}

	@Bean
	public BoardRepository boardRepository(){
		return new JpaBoardRepository(em);
	}
}
