package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.MemberDto;
import com.study.board.domain.board.entity.Member;
import com.study.board.domain.board.model.MemberAssembler;
import com.study.board.domain.board.repository.MemberRepository;
import com.study.global.exception.ErrorCode;
import com.study.global.member.MemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;


    /**
     * 회원가입
     *
     * @param memberDto
     */
    public MemberDto join(MemberDto memberDto) {
        Member entity = memberDto.toEntity(memberDto);
        return MemberAssembler.toMemberForm(memberRepository.save(entity));
    }

    /**
     * 로그인
     *
     * @param memberDto
     * @return
     */
    public MemberDto login(MemberDto memberDto) {
        Member logined = getMember(memberDto);
        return MemberAssembler.toMemberForm(logined);
    }


    /**
     * 회원조회
     *
     * @param memberDto
     * @return
     */
    public Member getMember(MemberDto memberDto) {
        Member member = memberRepository.findById(memberDto.getId())
                .orElseThrow(()->new MemberException(ErrorCode.ENTITY_NOT_FOUND));
        return member;
    }
}
