package com.study.unit.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.domain.board.controller.BoardController;
import com.study.domain.board.service.BoardService;
import com.study.global.aop.logtrace.LogTraceAspect;
import com.study.unit.domain.board.BoardConstantForTest;

@WebMvcTest({BoardController.class})
@ActiveProfiles("test")
public class ControllerTest extends BoardConstantForTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@MockBean
	protected BoardService boardService;

	@MockBean
	protected LogTraceAspect logTraceAspect;

}
