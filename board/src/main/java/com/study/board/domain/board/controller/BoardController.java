package com.study.board.domain.board.controller;

import com.study.board.domain.board.dto.BoardForm;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.repository.BoardRepository;
import com.study.board.domain.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j  //  로깅을 위한 어노테이션
public class BoardController {

    @Autowired  //  스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;


    /**
     * 게시판 목록
     *
     * @param model
     * @return
     */
    @GetMapping("/articles")
    public String index(Model model) {

        //  1. 모든 Article을 가져온다
        List<Board> articleEntityList = boardRepository.findAll();

        //  2. 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        //  3. 뷰 페이지를 설정
        return "articles/index";    //  articles/index.html
    }


    /**
     * 게시판 상세
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        //  1. id로 데이터를 가져옴!
        Board boardEntity = boardRepository.findById(id).orElse(null);

        //  2. 가져온 데이터를 모델에 등록
        model.addAttribute("board", boardEntity);

        return "articles/show";
    }


    /**
     * 게시판 등록
     *
     * @return
     */
    @GetMapping({"/articles/new"})
    public String newArticleForm() { return "articles/new"; }


    /**
     * 게시판 등록 처리
     *
     * @param form
     * @return
     */
    @PostMapping("/articles/create")
    public String createArticle(BoardForm form) {
        log.info(form.toString());

        // 1. DTO -> Entity 변환
        Board board = form.toEntity();
        log.info(board.toString());

        board.setWriter("홍의표");

        // 2. Repository에서 Entity를 DB안에 저장
        Board saved = boardRepository.save(board);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }


    /**
     * 게시판 수정
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {

        // 데이터 가져오기
        Board boardEntity = boardRepository.findById(id).orElse(null);

        // 모델에 데이터 등록
        model.addAttribute("board", boardEntity);

        // 뷰 페이지 설정
        return "articles/edit";
    }


    /**
     * 게시판 수정 처리
     *
     * @param form
     * @return
     */
    @PostMapping("articles/update")
    public String update(BoardForm form, RedirectAttributes rttr) {
        log.info(form.toString());

        //  DTO를 Entity로 변환
        Board boardEntity = form.toEntity();
        log.info("boardEntity : " + boardEntity.toString());

        //  Entity를 DB에 저장
        Board target = boardRepository.findById(boardEntity.getId()).orElse(null);

        if (target != null) {
            boardRepository.save(boardEntity);
            rttr.addFlashAttribute("msg", "정상적으로 수정되었습니다.");
        } else {
            rttr.addFlashAttribute("msg", "오류가 발생하였습니다. 관리자에게 문의하시기 바랍니다.");
        }

        //   수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + boardEntity.getId();
    }


    /**
     * 게시판 삭제 처리
     *
     * @param id
     * @return
     */
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다!");

        //  삭제 대상을 가져온다
        Board target = boardRepository.findById(id).orElse(null);
        log.info(target.toString());

        //  대상을 삭제한다
        if (target != null) {
            boardRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
        } else {
            rttr.addFlashAttribute("msg", "오류가 발생하였습니다. 관리자에게 문의해주시기 바랍니다.");
        }

        return "redirect:/articles";
    }
}






















