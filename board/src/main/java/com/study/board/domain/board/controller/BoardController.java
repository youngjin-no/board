package com.study.board.domain.board.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.board.domain.board.model.BoardDto;
import com.study.board.domain.board.model.BoardDtoForPage;
import com.study.board.domain.board.model.BoardDtoForSave;
import com.study.board.domain.board.model.BoardDtoForUpdate;
import com.study.board.domain.board.model.BoardSearchCond;
import com.study.board.domain.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
@Slf4j
public class BoardController {

	private final BoardService boardService;

	@PostMapping()
	public ResponseEntity<BoardDto> saveBoard(@Valid @RequestBody BoardDtoForSave boardSaveDto) {
		return ResponseEntity.ok().body(boardService.saveBoard(boardSaveDto));
	}

	@GetMapping("/{boardId}")
	public ResponseEntity<BoardDto> getBoardDetail(@PathVariable Long boardId) {
		return ResponseEntity.ok().body(boardService.getBoardDetail(boardId));
	}

	@GetMapping()
	public ResponseEntity<Page<BoardDtoForPage>> getBoardListWithPaging(BoardSearchCond cond, Pageable pageable) {
		log.info("pageable={}, cond={}", pageable, cond);
		return ResponseEntity.ok().body(boardService.getBoardListWithPage(cond, pageable));
	}

	@PutMapping("/{boardId}")
	public ResponseEntity<BoardDto> editBoard(@PathVariable Long boardId, @Valid BoardDtoForUpdate boardDto) {
		return ResponseEntity.ok().body(boardService.editBoard(boardId, boardDto));
	}

	@DeleteMapping("/{boardId}")
	public ResponseEntity<Boolean> deleteBoard(@PathVariable Long boardId) {
		return ResponseEntity.ok().body(boardService.deleteBoard(boardId));
	}

}
