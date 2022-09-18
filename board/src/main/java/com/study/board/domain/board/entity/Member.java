package com.study.board.domain.board.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
public class Member extends BaseTimeEntity{

    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String pw;

}
