package com.study.board.domain.board.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.board.domain.board.entity.Board;

public interface BoardRepository {
	List<Board> findAll();
	Board save(Board board);
	Optional<Board> findBySubject(String subject);
	Optional<Board> findByWriter(String writer);
}
