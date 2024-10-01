package com.jhs.studygroup.board.controller;

import com.jhs.studygroup.board.dto.CommentDTO;
import com.jhs.studygroup.board.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/{id}")
    public String commentSave(@PathVariable("id") Long id, @RequestParam("comment") String comment, Model model, HttpSession session) {
        //        회원 아이디
        Long userIdObj = (Long) session.getAttribute("userId");
        long userId = (userIdObj != null) ? userIdObj : 0L;

        //        회원 이름
        String userName = (String) session.getAttribute("userName");

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setBoard_id(id);
        commentDTO.setUser_id(userId);
        commentDTO.setContent(comment);
        commentDTO.setUserName(userName);
        commentService.commentSave(commentDTO);


        return "redirect:/detail/" + id;
    }

    @GetMapping("/commentDelete/{board_id}/{id}")
    public String commentDelete(@PathVariable("board_id") Long board_id,@PathVariable("id") Long id, Model model, HttpSession session) {

        commentService.commentDelete(id);

        return "redirect:/detail/" + board_id;
    }
}
