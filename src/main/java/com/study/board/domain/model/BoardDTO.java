package com.study.board.domain.model;

import com.study.board.domain.entity.Board;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto created ID
    private Long id;
    private String subject;
    @Lob
    private String contents;
    @Column(length = 20)
    private String writer;
    @Column(name = "DELETE_YN",length = 1)
    private boolean isDelete = false;
    private String password;

    public BoardDTO(Board board) {
        this.id = board.getId();
        this.subject = board.getSubject();
        this.contents = board.getContents();
        this.writer = board.getWriter();
        this.isDelete = board.isDelete();
    }

        public Board toEntity(BoardDTO board) {
        return Board.builder()
                .subject(subject)
                .contents(contents)
                .writer(writer)
                .isDelete(isDelete)
                .build();
    }
}
