package com.study.board.domain.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

	private final BoardRepository boardRepository;

	public List<Board> findBoards() {
		return boardRepository.findAll();
	}

	public Optional<Board> findWriter(String writer){
		return boardRepository.findByWriter(writer);
	}

	public Optional<Board> findSubject(String subject){
		return boardRepository.findBySubject(subject);
	}

}
