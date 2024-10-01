package com.jhs.studygroup.board.controller;

import com.jhs.studygroup.board.dto.BoardDTO;
import com.jhs.studygroup.board.dto.LikeDTO;
import com.jhs.studygroup.board.service.LikeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LikeController {
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/like/{id}")
    public String like(@PathVariable Long id, HttpSession session,Model model) {
        Long userIdObj = (Long) session.getAttribute("userId");
        long userId = (userIdObj != null) ? userIdObj : 0L;

        if(userIdObj==null){
            return "redirect:/login";
        }

        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setUser_id(userIdObj);
        likeDTO.setBoard_id(id);

        likeService.plus(likeDTO);
        likeService.boardLike(id);

        return "redirect:/detail/{id}";
    }

    @GetMapping("/dislike/{id}")
    public String dislike(@PathVariable Long id, HttpSession session) {
        Long userIdObj = (Long) session.getAttribute("userId");
        long userId = (userIdObj != null) ? userIdObj : 0L;

        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setUser_id(userIdObj);
        likeDTO.setBoard_id(id);

        likeService.minus(likeDTO);
        likeService.boardDisLike(id);

        return "redirect:/detail/{id}";
    }
}
