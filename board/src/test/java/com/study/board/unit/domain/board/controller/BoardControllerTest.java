package com.study.board.unit.domain.board.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.study.board.domain.board.controller.BoardController;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.model.BoardDto;
import com.study.board.domain.board.model.BoardDtoAssembler;
import com.study.board.domain.board.model.BoardDtoForSave;
import com.study.board.domain.board.model.BoardDtoForUpdate;
import com.study.board.unit.domain.ControllerTest;

public class BoardControllerTest extends ControllerTest {

	private static final String BASE_URL = "/api/boards";

	@DisplayName("게시판 저장 서비스")
	@Test
	void saveBoardTest() throws Exception {
		BoardDtoForSave saveDto = BoardDtoForSave.builder()
			.subject("test")
			.contents("test contents")
			.writer("tester")
			.password("1234")
			.build();

		Board board = BoardDtoAssembler.boardFromSaveDto(saveDto);
		BoardDto boardDto = BoardDtoAssembler.toBoardDto(board);

		given(boardService.saveBoard(any(BoardDtoForSave.class))).willReturn(boardDto);

		ResultActions result = mockMvc.perform(
			post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(saveDto)));

		result.andDo(print()).andExpect(status().isOk())
			.andExpect(handler().handlerType(BoardController.class))
			.andExpect(jsonPath("$.subject").value(boardDto.getSubject()))
			.andExpect(jsonPath("$.contents").value(boardDto.getContents()))
			.andExpect(jsonPath("$.writer").value(boardDto.getWriter()));
	}

	@DisplayName("게시판 상세정보 서비스")
	@Test
	void getBoardDetailTest() throws Exception {
		Long boardId = 1L;
		Board saveDto = Board.builder()
			.id(boardId)
			.subject("test")
			.contents("test contents")
			.writer("tester")
			.password("1234")
			.build();
		BoardDto boardDto = BoardDtoAssembler.toBoardDto(saveDto);

		given(boardService.getBoardDetail(anyLong())).willReturn(boardDto);

		ResultActions result = mockMvc.perform(
			get(BASE_URL + "/" + boardId).contentType(MediaType.APPLICATION_JSON));

		result.andDo(print()).andExpect(status().isOk())
			.andExpect(handler().handlerType(BoardController.class))
			.andExpect(jsonPath("$.id").value(boardId))
			.andExpect(jsonPath("$.subject").value(boardDto.getSubject()))
			.andExpect(jsonPath("$.contents").value(boardDto.getContents()))
			.andExpect(jsonPath("$.writer").value(boardDto.getWriter()));
	}

	@DisplayName("게시판 리스트 페이징 서비스")
	@Test
	void getBoardListWithPageTest() throws Exception {

	}

	@DisplayName("게시판 수정 서비스")
	@Test
	void updateBoardTest() throws Exception {
		Long boardId = 1L;
		BoardDtoForUpdate updateDto = BoardDtoForUpdate.builder()
			.subject("test update")
			.contents("test update contents")
			.writer("tester")
			.build();
		BoardDto boardDto = BoardDtoAssembler.boardDtoFromUpdateDto(updateDto);

		given(boardService.editBoard(anyLong(), any(BoardDtoForUpdate.class)))
			.willReturn(boardDto);

		mockMvc.perform(
			put(BASE_URL + "/" + boardId).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updateDto)))
			.andDo(print()).andExpect(status().isOk())
			.andExpect(handler().handlerType(BoardController.class))
			.andExpect(jsonPath("$.subject").value(boardDto.getSubject()))
			.andExpect(jsonPath("$.contents").value(boardDto.getContents()))
			.andExpect(jsonPath("$.writer").value(boardDto.getWriter()));
	}

	@DisplayName("게시판 삭제 서비스")
	@Test
	void deleteBoardTest() throws Exception {

	}
}
