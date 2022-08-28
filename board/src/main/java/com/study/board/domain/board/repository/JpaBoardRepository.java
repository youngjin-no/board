package com.study.board.domain.board.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import com.study.board.domain.board.entity.Board;

public class JpaBoardRepository implements BoardRepository {

	private final EntityManager em;

	public JpaBoardRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Board> findAll() {
		return em.createQuery("select b from Board b", Board.class)
			.getResultList();
	}

	@Override
	public Board save(Board board) {
		em.persist(board); // jpa가 insert 쿼리 생성
		return board;
	}

	@Override
	public Optional<Board> findBySubject(String subject) {
		List<Board> result = em.createQuery("select b from Board b where b.subject = :subjct", Board.class)
			.setParameter("subjct", subject)
			.getResultList();

		return result.stream().findAny();
	}

	@Override
	public Optional<Board> findByWriter(String writer) {
		List<Board> result = em.createQuery("select b from Board b where b.writer = :writer", Board.class)
			.setParameter("writer", writer)
			.getResultList();

		return result.stream().findAny();
	}
}
