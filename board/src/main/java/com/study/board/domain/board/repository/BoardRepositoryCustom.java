package com.study.board.domain.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.study.board.domain.board.model.BoardDto;
import com.study.board.domain.board.model.BoardDtoForPage;
import com.study.board.domain.board.model.BoardSearchCond;

public interface BoardRepositoryCustom {
	Page<BoardDtoForPage> getBoardListWithPage(BoardSearchCond cond, Pageable pageable);
}
