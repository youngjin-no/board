package com.study.board.domain.board.dto;

import com.study.board.domain.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class BoardForm {

    private Long id;
    private String subject;
    private String contents;

    public Board toEntity(){
        return Board.builder()
                .id(id)
                .subject(subject)
                .contents(contents)
                .build();
    }

}
