package com.study.board.domain.board.api;

import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController     //  RestAPI 용 컨트롤러 --> 데이터(JSON)를 반환
@Slf4j
public class BoardApiController {

    @Autowired  //  DI, 생성 객체를 가져와 연결
    private BoardService boardService;

    //  GET
    @GetMapping("/api/articles")
    public List<Board> index() {
        return boardService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Board show(@PathVariable Long id) {
        return boardService.show(id);
    }

}
