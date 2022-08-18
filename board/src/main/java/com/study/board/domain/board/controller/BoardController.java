package com.study.board.domain.board.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.board.domain.board.model.BoardDto;
import com.study.board.domain.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/{boardId}")
	public ResponseEntity<BoardDto> getBoardDetail(@PathVariable Long boardId) {
		return ResponseEntity.ok().body(boardService.getBoardDetail(boardId));
	}

	@PutMapping("/{boardId}")
	public ResponseEntity<BoardDto> editBoard(@PathVariable Long boardId, @Valid BoardDto boardDto) {
		return ResponseEntity.ok().body(boardService.editBoard(boardId, boardDto));
	}

	@DeleteMapping("/{boardId}")
	public ResponseEntity<Boolean> deleteBoard(@PathVariable Long boardId) {
		return ResponseEntity.ok().body(boardService.deleteBoard(boardId));
	}

}
