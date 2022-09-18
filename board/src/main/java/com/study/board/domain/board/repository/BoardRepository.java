package com.study.board.domain.board.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.board.domain.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Override
    List<Board> findAll();

}

