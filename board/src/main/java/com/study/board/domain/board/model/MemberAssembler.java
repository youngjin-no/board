package com.study.board.domain.board.model;

import com.study.board.domain.board.dto.MemberDto;
import com.study.board.domain.board.entity.Member;

public class MemberAssembler {

    public static MemberDto toMemberForm(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .pw(member.getPw())
                .build();
    }

}
