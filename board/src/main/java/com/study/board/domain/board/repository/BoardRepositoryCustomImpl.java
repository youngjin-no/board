package com.study.board.domain.board.repository;

import static com.study.board.domain.board.entity.QBoard.*;
import static org.springframework.util.StringUtils.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.model.BoardDtoForPage;
import com.study.board.domain.board.model.BoardSearchCond;
import com.study.board.domain.board.model.QBoardDtoForPage;

public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public BoardRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Page<BoardDtoForPage> getBoardListWithPage(BoardSearchCond cond, Pageable pageable) {
		List<BoardDtoForPage> content = queryFactory
			.select(new QBoardDtoForPage(
				board.id,
				board.subject,
				board.contents,
				board.writer,
				board.isDelete,
				board.createdDate,
				board.lastModifiedDate
			))
			.from(board)
			.where(writerEq(cond.getWriter()),
				subjectLike(cond.getSubject()))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Board> countQuery = queryFactory
			.select(board)
			.from(board)
			.where(writerEq(cond.getWriter()),
				subjectLike(cond.getSubject()))
			.offset(pageable.getOffset());

		return PageableExecutionUtils.getPage(content, pageable,()->countQuery.fetch().size());
	}

	private BooleanExpression writerEq(String writer) {
		return hasLength(writer) ? board.writer.eq(writer) : null;
	}

	private BooleanExpression subjectLike(String subject) {
		return hasLength(subject) ? board.subject.contains(subject) : null;
	}
}
