package com.study.unit.domain.board.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.study.domain.board.controller.BoardController;
import com.study.domain.board.entity.Board;
import com.study.domain.board.model.BoardDto;
import com.study.domain.board.model.BoardDtoAssembler;
import com.study.domain.board.model.BoardDtoForSave;
import com.study.domain.board.model.BoardDtoForUpdate;
import com.study.global.exception.ErrorCode;
import com.study.global.exception.board.BoardException;
import com.study.unit.domain.ControllerTest;

public class BoardControllerTest extends ControllerTest {
	public static final String redirectURL = BASE_URL + "/" + BOARD_ID;

	@DisplayName("게시판 저장 서비스")
	@Test
	void saveBoardTest() throws Exception {
		BoardDtoForSave saveDto = getSaveDto();
		Board board = getBoard();
		BoardDto boardDto = BoardDtoAssembler.toBoardDto(board);
		given(boardService.saveBoard(any(BoardDtoForSave.class))).willReturn(boardDto);

		mockMvc.perform(
				post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(saveDto)))
			.andDo(print()).andExpect(status().isCreated())
			.andExpect(handler().handlerType(BoardController.class))
			.andExpect(redirectedUrl(redirectURL));
	}

	@DisplayName("게시판 상세정보 서비스")
	@Test
	void getBoardDetailTest() throws Exception {
		Board saveDto = getBoard();
		BoardDto boardDto = BoardDtoAssembler.toBoardDto(saveDto);
		given(boardService.getBoardDetail(anyLong())).willReturn(boardDto);

		mockMvc.perform(
				get(BASE_URL + "/" + BOARD_ID).contentType(MediaType.APPLICATION_JSON))
			.andDo(print()).andExpect(status().isOk())
			.andExpect(handler().handlerType(BoardController.class))
			.andExpect(jsonPath("$.id").value(BOARD_ID))
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
		BoardDtoForUpdate updateDto = getUpdateDto(PASSWORD);
		Board board = getBoard();
		given(boardService.editBoard(anyLong(), any(BoardDtoForUpdate.class)))
			.willReturn(1L);

		mockMvc.perform(
				put(redirectURL).contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(updateDto)))
			.andDo(print()).andExpect(status().isCreated())
			.andExpect(handler().handlerType(BoardController.class))
			.andExpect(redirectedUrl(redirectURL));
	}

	@DisplayName("게시판 수정 서비스 - 비밀번호 틀렸을 경우 예외 발생")
	@Test
	void updateBoardExceptionTest() throws Exception {
		BoardDtoForUpdate updateDto = getUpdateDto("");
		Board board = getBoard();
		given(boardService.editBoard(anyLong(), any(BoardDtoForUpdate.class)))
			.willThrow(new BoardException(ErrorCode.INVALID_PASSWORD));

		mockMvc.perform(
				put(redirectURL).contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(updateDto)))
			.andDo(print()).andExpect(status().is4xxClientError())
			.andExpect(handler().handlerType(BoardController.class))
			.andExpect(jsonPath("$.message").value(ErrorCode.INVALID_PASSWORD.getMessage()));
	}

	@DisplayName("게시판 삭제 서비스")
	@Test
	void deleteBoardTest() throws Exception {
		given(boardService.deleteBoard(anyLong())).willReturn(true);

		mockMvc.perform(
				delete(BASE_URL + "/" + BOARD_ID).contentType(MediaType.APPLICATION_JSON)
			)
			.andDo(print()).andExpect(status().isOk())
			.andExpect(handler().handlerType(BoardController.class))
			.andExpect(jsonPath("$").value(true));
	}

	@DisplayName("게시판 삭제 서비스 실패 - 예외발생")
	@Test
	void deleteBoardExceptionTest() throws Exception {
		given(boardService.deleteBoard(anyLong())).willThrow(new BoardException(ErrorCode.ENTITY_NOT_FOUND));

		mockMvc.perform(
				delete(BASE_URL + "/" + 2L).contentType(MediaType.APPLICATION_JSON)
			)
			.andDo(print()).andExpect(status().is4xxClientError())
			.andExpect(handler().handlerType(BoardController.class))
			.andExpect(jsonPath("$.message").value(ErrorCode.ENTITY_NOT_FOUND.getMessage()));
	}

	private BoardDtoForSave getSaveDto() {
		return BoardDtoForSave.builder()
			.subject(SUBJECT)
			.contents(CONTENTS)
			.writer(WRITER)
			.password(PASSWORD)
			.build();
	}

	private BoardDtoForUpdate getUpdateDto(String password) {
		return BoardDtoForUpdate.builder()
			.subject(SUBJECT)
			.contents(CONTENTS)
			.writer(WRITER)
			.password(password)
			.build();
	}

	private Board getBoard() {
		return Board.builder()
			.id(BOARD_ID)
			.subject(SUBJECT)
			.contents(CONTENTS)
			.writer(WRITER)
			.password(PASSWORD)
			.build();
	}
}
