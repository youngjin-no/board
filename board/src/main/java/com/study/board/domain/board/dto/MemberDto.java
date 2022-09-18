package com.study.board.domain.board.dto;

import com.study.board.domain.board.entity.Member;
import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberDto {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String pw;

    public Member toEntity(MemberDto memberDto) {
        return Member.builder()
                .id(id)
                .name(name)
                .email(email)
                .phone(phone)
                .pw(pw)
                .build();
    }

}
