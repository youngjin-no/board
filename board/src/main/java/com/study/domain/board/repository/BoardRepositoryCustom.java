package com.study.domain.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.study.domain.board.model.BoardDtoForPage;
import com.study.domain.board.model.BoardSearchCond;

public interface BoardRepositoryCustom {
	Page<BoardDtoForPage> getBoardListWithPage(BoardSearchCond cond, Pageable pageable);
}
