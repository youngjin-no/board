package com.study.board.domain.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor //final 또는 @NonNull에 대해 생성자 생성
public class BoardController {

	private final BoardService boardService;

	/*
	 * 게시글 리스트 조회
	 * */
	@GetMapping("/boards")
	public List<Board> findAll() {
		return boardService.findBoards();
	}
}
