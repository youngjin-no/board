package com.study.board.unit.domain.board.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.study.board.domain.board.controller.BoardController;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.model.BoardDto;
import com.study.board.domain.board.model.BoardDtoAssembler;
import com.study.board.domain.board.model.BoardSaveDto;
import com.study.board.unit.domain.ControllerTest;

public class BoardControllerTest extends ControllerTest {

	@DisplayName("게시판 저장 서비스")
	@Test
	void saveBoardTest() throws Exception {
		BoardSaveDto saveDto = BoardSaveDto.builder()
			.subject("test")
			.contents("test contents")
			.writer("tester")
			.password("1234")
			.build();

		Board board = BoardDtoAssembler.boardFromSaveDto(saveDto);
		BoardDto boardDto = BoardDtoAssembler.toBoardDto(board);

		given(boardService.saveBoard(any(BoardSaveDto.class))).willReturn(boardDto);

		ResultActions result = mockMvc.perform(
			post("/api/boards").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(saveDto)));

		result.andDo(print()).andExpect(status().isOk())
			.andExpect(handler().handlerType(BoardController.class))
			.andExpect(jsonPath("$.subject").value(boardDto.getSubject()))
			.andExpect(jsonPath("$.contents").value(boardDto.getContents()))
			.andExpect(jsonPath("$.writer").value(boardDto.getWriter()));
	}
}
