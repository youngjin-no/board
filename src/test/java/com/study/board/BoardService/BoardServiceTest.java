package com.study.board.BoardService;

import com.study.board.domain.model.BoardDTO;
import com.study.board.service.BoardService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
//@DataJpaTest

public class BoardServiceTest {
    @Autowired
    private BoardService boardService;

//    @AfterEach
//    @Transactional
//    public void AfterEach() {
//        boardService.deleteAll();
//    }
    @Test
    public void boardDetail() {
        //given
        BoardDTO dto = new BoardDTO(1L, "DetailTest", "test1", "현겸", false, "test123", LocalDateTime.now());

        //when
        boardService.register(dto);
        BoardDTO boardDTO = boardService.detail(dto.getId());

        //then
        assertThat(boardDTO.getSubject()).isEqualTo(dto.getSubject());
    }
    @Test
    public void boardList() {
        //given
        int a=3;
        String[] writers = new String[]{"지수","재영","하나"};
        for (int i = 0; i < a; i++) {
            BoardDTO dto = BoardDTO.builder().subject("example" + i).contents("test contents")
                    .writer(writers[i % a])
                    .password("").build();
        //when
            boardService.register(dto);
        }
        //then
        List<BoardDTO> boards= boardService.boardList();
        assertThat(boards.size()).isEqualTo(a);

    }
    @Test
    public void boardUpdate() {
        //given
        BoardDTO dto = new BoardDTO(1L, "UpdateTest", "notUpdated", "현겸", false, "test123", LocalDateTime.now());
        //when
        boardService.register(dto);
        dto.setContents("updated");
        boardService.update(dto);
        BoardDTO updatedBoard=boardService.detail(dto.getId());
        //then
        assertThat(updatedBoard.getContents()).isEqualTo("updated");

    }

    @Test
    public void boardDelete() {
        //given
        BoardDTO dto1 = new BoardDTO(1L, "DeleteTest", "deleteTest1", "현겸", false, "test123", LocalDateTime.now());
        BoardDTO dto2 = new BoardDTO(2L, "DeleteTest", "deleteTest2", "현겸", false, "test123", LocalDateTime.now());
        boardService.register(dto1);
        boardService.register(dto2);
        //when
        boardService.delete(dto2.getId());
        List<BoardDTO> boardDTOS= boardService.boardList();
        //then
        assertThat(boardDTOS.size()).isEqualTo(1);

    }
    @Test
    public void updatePasswordFailTest() {
        //given
        BoardDTO dto = new BoardDTO(1L, "UpdateTest", "deleteTest1", "현겸", false, "password", LocalDateTime.now());
        //when
        boardService.register(dto);
        dto.setPassword("wrongPassword");
        dto.setContents("updated");
        Boolean result= boardService.update(dto);
        //then
        assertThat(result).isFalse();

    }
    @Test
    public void updatePasswordSuccessTest() {
        //given
        BoardDTO dto = new BoardDTO(1L, "UpdateTest", "deleteTest1", "현겸", false, "password", LocalDateTime.now());
        //when
        boardService.register(dto);
        dto.setPassword("password");
        dto.setContents("updated");
        Boolean result= boardService.update(dto);
        //then
        assertThat(result).isTrue();
    }

}
