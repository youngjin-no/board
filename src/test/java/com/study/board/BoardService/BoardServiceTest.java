package com.study.board.BoardService;

import com.study.board.domain.entity.Board;
import com.study.board.domain.entity.repository.BoardRepository;
import com.study.board.domain.model.BoardDTO;
import com.study.board.service.BoardService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardServiceTest {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    EntityManager em;
    @Autowired
    private BoardService boardService;

    @Test
    @Transactional
    public void boardDetail() {
        //given
        Board board = Board.builder().subject("DetailTest").writer("현겸").contents("test").build();
        em.persist(board);
        //when
        boardRepository.save(board);
        BoardDTO boardDTO = boardService.detail(board.getId());

        //then
        assertThat(boardDTO.getSubject()).isEqualTo(board.getSubject());
    }

    @Test
    @Transactional
    public void boardList() {
        //given
        int a=3;
        String[] writers = new String[]{"지수","재영","하나"};
        for (int i = 0; i < a; i++) {
            Board board = Board.builder().subject("example" + i).contents("test contents")
                    .writer(writers[i % a])
                    .password("").build();
        //when
            em.persist(board);
            boardRepository.save(board);
        }
        //then
        List<BoardDTO> boards= boardService.boardList();
        assertThat(boards.size()).isEqualTo(a);

    }
    @Test
    @Transactional
    public void boardUpdate() {
        //given
        Board board = Board.builder().subject("UpdateTest").writer("현겸").contents("notUpdated").build();
        //when
        em.persist(board);
        boardRepository.save(board);
        BoardDTO dto= new BoardDTO(board);
        dto.setContents("updated");
        Board updatedBoard= dto.toEntity(dto);
        boardRepository.save(updatedBoard);
        //then
        assertThat(board.getContents()).isEqualTo("updated");

    }
//
    @Test
    @Transactional
    public void boardDelete() {
        //given
        Board board1= Board.builder().subject("DeleteTest1").writer("현겸").contents("deleteTest1").build();
        Board board2= Board.builder().subject("DeleteTest2").writer("현겸").contents("deleteTest2").build();
        em.persist(board1);
        em.persist(board2);
        boardRepository.save(board1);
        boardRepository.save(board2);
        //when
        boardService.delete(board2.getId());
        List<BoardDTO> boardDTOS= boardService.boardList();
        //then
        assertThat(boardDTOS.size()).isEqualTo(1);

    }
    @Test
    @Transactional
    public void updatePasswordFailTest() {
        //given
        Board board= Board.builder().subject("passowordTest").writer("현겸").contents("notUpdated").password("password").build();
        //when
        em.persist(board);
        boardRepository.save(board);
        BoardDTO dto= new BoardDTO(board);
        dto.setPassword("wrongPassword");
        dto.setContents("updated");
        Boolean isUpdated= boardService.update(dto);
        //then
        assertThat(isUpdated).isFalse();

    }
    @Test
    @Transactional
    public void updatePasswordSuccessTest() {
        //given
        Board board= Board.builder().subject("passowordTest").writer("현겸").contents("notUpdated").password("password").build();
        //when
        em.persist(board);
        boardRepository.save(board);
        BoardDTO dto= new BoardDTO(board);
        dto.setPassword("password");
        dto.setContents("updated");
        Boolean isUpdated= boardService.update(dto);
        //then
        assertThat(isUpdated).isTrue();
    }

}
