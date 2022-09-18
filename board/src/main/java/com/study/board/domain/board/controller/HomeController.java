package com.study.board.domain.board.controller;

import com.study.board.domain.board.SessionConst;
import com.study.board.domain.board.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {


    /**
     * 홈
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "loginForm";
        }

        MemberDto loginMember = (MemberDto)session.getAttribute(SessionConst.LOGIN_MEMBER);

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "loginForm";
        }

        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);

        return "redirect:/articles";
    }

}
