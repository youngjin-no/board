package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.BoardDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    @Autowired  //DI
    private BoardRepository boardRepository;

    public List<Board> index() {
        return boardRepository.findAll();
    }

    public Board create(BoardDto dto) {
        Board board = dto.toEntity();

        if(board.getId() != null) {
            return null;
        }

        return boardRepository.save(board);
    }

    public Board show(Long id) {
        return boardRepository.findById(id).orElse(null);
    }
}
