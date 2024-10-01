package com.jhs.studygroup.board.controller;

import com.jhs.studygroup.board.dto.BoardDTO;
import com.jhs.studygroup.board.dto.CommentDTO;
import com.jhs.studygroup.board.dto.LikeDTO;
import com.jhs.studygroup.board.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/add")
    public String addBoard(HttpSession session, Model model) {

//        회원 아이디
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);

        return "addBoard";
    }

    @PostMapping("/add")
    public String addBoard(HttpSession session, BoardDTO boardDTO) throws IOException {
        String userName = (String) session.getAttribute("userName");
        boardDTO.setUserName(userName);
        System.out.println("boardDTO = " + boardDTO);
        System.out.println("boardDTO = " + boardDTO.getUserName());
        boardService.save(boardDTO);
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String findAll(@RequestParam(defaultValue = "1") int page,
                          @RequestParam(value = "keyword", required = false) String keyword, HttpSession session,
                          Model model) throws Exception {


//        회원 아이디
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);

        int pageSize = 5;
        //tap 별 List 불러오는 로직
        List<BoardDTO> boardDTOtap1 = boardService.findAll(keyword, page, pageSize);
        model.addAttribute("boardTap1", boardDTOtap1);

        int totalCount = boardService.totalPage();
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);  // 총 페이지 수 계산

        // 페이지 그룹 계산
        int pageGroupSize = 5;  // 페이지 그룹 크기 (한 번에 보여줄 페이지 번호 수)
        int currentGroup = (int) Math.ceil((double) page / pageGroupSize);  // 현재 페이지 그룹
        int startPage = (currentGroup - 1) * pageGroupSize + 1;  // 현재 그룹의 첫 번째 페이지
        int endPage = Math.min(currentGroup * pageGroupSize, totalPages);  // 현재 그룹의 마지막 페이지

        model.addAttribute("pagedData", boardDTOtap1);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "main";
    }

    @GetMapping("/mainLikeTap")
    public String mainLikeTap(@RequestParam(defaultValue = "1") int page2,
                              @RequestParam(value = "keyword", required = false) String keyword, HttpSession session,
                              Model model) throws Exception {


//        회원 아이디
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);

        int pageSize = 5;

        // 페이지 그룹 계산
        int pageGroupSize = 5;  // 페이지 그룹 크기 (한 번에 보여줄 페이지 번호 수)

        // boardDTOtap2 페이징 처리//좋아요순
        List<BoardDTO> boardDTOtap2 = boardService.findMostLike(keyword, page2, pageSize);
        int totalCount2 = boardService.totalPage();
        int totalPages2 = (int) Math.ceil((double) totalCount2 / pageSize);  // 총 페이지 수 계산
        int currentGroup2 = (int) Math.ceil((double) page2 / pageGroupSize);  // 현재 페이지 그룹
        int startPage2 = (currentGroup2 - 1) * pageGroupSize + 1;  // 현재 그룹의 첫 번째 페이지
        int endPage2 = Math.min(currentGroup2 * pageGroupSize, totalPages2);  // 현재 그룹의 마지막 페이지

        model.addAttribute("boardTap2", boardDTOtap2);
        model.addAttribute("currentPage2", page2);
        model.addAttribute("totalPages2", totalPages2);
        model.addAttribute("startPage2", startPage2);
        model.addAttribute("endPage2", endPage2);
        return "mainLikeTap";
    }

    @GetMapping("/mainMyBoard")
    public String mainMyBoard(@RequestParam(defaultValue = "1") int page3,
                              @RequestParam(value = "keyword", required = false) String keyword, HttpSession session,
                              Model model) throws Exception {


//        회원 아이디
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);

        int pageSize = 5;
        int pageGroupSize = 5;  // 페이지 그룹 크기 (한 번에 보여줄 페이지 번호 수)


        // boardDTOtap3 페이징 처리
        List<BoardDTO> boardDTOtap3 = boardService.myBoard(userName, page3, pageSize);
        int totalCount3 = boardService.totalPageTap3(userName);  // boardDTOtap3에 대한 총 데이터 수를 반환해야 해
        int totalPages3 = (int) Math.ceil((double) totalCount3 / pageSize);  // 총 페이지 수 계산
        int currentGroup3 = (int) Math.ceil((double) page3 / pageGroupSize);  // 현재 페이지 그룹
        int startPage3 = (currentGroup3 - 1) * pageGroupSize + 1;  // 현재 그룹의 첫 번째 페이지
        int endPage3 = Math.min(currentGroup3 * pageGroupSize, totalPages3);  // 현재 그룹의 마지막 페이지

        model.addAttribute("boardTap3", boardDTOtap3);
        model.addAttribute("currentPage3", page3);
        model.addAttribute("totalPages3", totalPages3);
        model.addAttribute("startPage3", startPage3);
        model.addAttribute("endPage3", endPage3);


        return "mainMyBoard";
    }


    @GetMapping("detail/{id}")
    public String findById(@PathVariable("id") Long id, Model model, HttpSession session) {

//        회원 이름
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);

        //회원 id
        Long userIdObj = (Long) session.getAttribute("userId");
        long userId = (userIdObj != null) ? userIdObj : 0L;

        //조회수 처리
        boardService.updateHits(id);

        //상세내용 가져옴
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        System.out.println("boardDTO = " + boardDTO);

        //좋아요
        Long getLikeCount = boardService.getLikeCount(id);
        model.addAttribute("isLiked", getLikeCount); //LikeDTO


        //회원이 좋아요 누른 여부
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setUser_id(userId);
        likeDTO.setBoard_id(id);
        boolean isLikedByUser = boardService.isLikedByUser(likeDTO);
        model.addAttribute("isLikedByUser", isLikedByUser);

        //댓글
        List<CommentDTO> commentDTOList=boardService.getComment(id);
        model.addAttribute("commentDTOList",commentDTOList);

        return "detail";
    }

    @GetMapping("/editBoard/{id}")
    public String editBoard(@PathVariable("id") Long id, Model model, HttpSession session) {
//        회원 아이디
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);

        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "editBoard";
    }

    @PostMapping("/editBoard/{id}")
    public String editBoard(BoardDTO boardDTO, Model model, HttpSession session) {

//        회원 아이디
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);

        boardService.update(boardDTO);
        BoardDTO dto = boardService.findById(boardDTO.getId());
        model.addAttribute("board", dto);
        return "redirect:/main";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {

//        회원 아이디
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);

        boardService.delete(id);
        return "redirect:/main";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();  // 세션 만료 (세션 종료)
        return "redirect:/main";  // 로그아웃 후 로그인 페이지로 리다이렉트
    }


}
