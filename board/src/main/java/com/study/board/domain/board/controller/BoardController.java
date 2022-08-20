package com.study.board.domain.board.controller;

import com.study.board.domain.board.model.BoardDto;
import com.study.board.domain.board.model.BoardDtoForPage;
import com.study.board.domain.board.model.BoardSaveDto;
import com.study.board.domain.board.model.BoardSearchCond;
import com.study.board.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
@Slf4j
public class BoardController {

	private final BoardService boardService;

	@PostMapping()
	public ResponseEntity<BoardDto> saveBoard(@Valid @RequestBody BoardSaveDto boardSaveDto) {
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
	public ResponseEntity<BoardDto> editBoard(@PathVariable Long boardId, @Valid BoardDto boardDto) {
		return ResponseEntity.ok().body(boardService.editBoard(boardId, boardDto));
	}

	@DeleteMapping("/{boardId}")
	public ResponseEntity<Boolean> deleteBoard(@PathVariable Long boardId) {
		return ResponseEntity.ok().body(boardService.deleteBoard(boardId));
	}

}
