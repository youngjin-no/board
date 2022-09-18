package com.study.board.domain.board.controller;

import com.study.board.domain.board.repository.MemberRepository;
import com.study.board.domain.board.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class MemberViewController {

    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberService memberService;

    /**
     * 로그인
     *
     * @return
     */
    @GetMapping("/login")
    public String Login() {
        return "loginForm";
    }

    /**
     * 회원가입
     *
     * @return
     */
    @GetMapping("/join")
    public String Join() {return "joinForm"; }

    /**
     * 로그아웃
     *
     * @return
     */
    @GetMapping("/logout")
    public String LogoutProc(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "loginForm";
    }

}
