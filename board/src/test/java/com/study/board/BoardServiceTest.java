package com.study.board;

import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest     // 해당 클래스는 스프링부트와 연됭되어 테스팅된다
public class BoardServiceTest {

    @Autowired BoardService boardService;

    @Test
    void index() {

        // 예상
        Board a = new Board(1L, "example0", "test contents");
        Board b = new Board(2L, "example1", "test contents");
        Board c = new Board(3L, "example2", "test contents");

        List<Board> expected = new ArrayList<Board>(Arrays.asList(a,b,c));

        // 실제
        List<Board> articles = boardService.index();

        // 비교
        assertEquals(expected.toString(), articles.toArray());


    }

}
