package com.study.board.domain.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardViewController {

	private final BoardService boardService;

	/*
	* 게시판 리스트 화면
	* */
	@GetMapping("/list")
	public String boardList(Model model) {
		List<Board> boards = boardService.findBoards();
		model.addAttribute("boards", boards);
		return "board/list";
	}

	/*
	* 게시판 등록 화면
	* */
	@GetMapping("/write")
	public String boardWrite(){
		return "board/write";
	}

}
