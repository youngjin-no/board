package com.study.board.domain.board.controller;

import com.study.board.domain.board.SessionConst;
import com.study.board.domain.board.dto.MemberDto;
import com.study.board.domain.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;


    /**
     * 로그인 처리
     *
     * @param memberDto
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<MemberDto> LoginProc(@ModelAttribute MemberDto memberDto, HttpServletRequest request) {

        MemberDto loginMember = memberService.login(memberDto);

        // 로그인 성공 처리
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return ResponseEntity.ok().body(loginMember);
    }

    /**
     * 회원가입 처리
     *
     * @param memberDto
     * @return
     */
    @PostMapping("/join")
    public ResponseEntity<MemberDto> JoinProc(MemberDto memberDto) {
        return ResponseEntity.ok().body(memberService.join(memberDto));
    }

}
