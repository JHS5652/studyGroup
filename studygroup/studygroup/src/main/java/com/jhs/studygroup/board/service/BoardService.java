package com.jhs.studygroup.board.service;

import com.jhs.studygroup.board.dto.BoardDTO;
import com.jhs.studygroup.board.dto.CommentDTO;
import com.jhs.studygroup.board.dto.LikeDTO;
import com.jhs.studygroup.board.dto.UserDTO;
import com.jhs.studygroup.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void save(BoardDTO boardDTO) {
        boardRepository.save(boardDTO);
    }

    public List<BoardDTO> findAll(String keyword, int page, int size) {
        int offset = (page - 1) * size;
        return boardRepository.findAll(keyword,size,offset);
    }

    public List<BoardDTO> getRecruitingBoards() {
        return boardRepository.getRecruitingBoards();
    }

    public List<BoardDTO> closeRecruitingBoards() {
        return boardRepository.closeRecruitingBoards();
    }

    public List<BoardDTO> myBoard(String userName, int page, int size) {
        int offset = (page - 1) * size;
        return boardRepository.myBoard(userName,size,offset);
    }

    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }


    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
    }


    public Long getLikeCount(Long id) {

        List<LikeDTO> likeDTO = boardRepository.getLikeCount(id);

        if(likeDTO != null) {
            return (long)likeDTO.size();
        }
        return 0l;
    }

    public boolean isLikedByUser(LikeDTO likeDTO) {
        return boardRepository.isLikedByUser(likeDTO);
    }

    public List<BoardDTO> findMostLike(String keyword, int page,int size) {
        int offset = (page - 1) * size;
        return boardRepository.findMostLike(keyword,size,offset);
    }

    public List<BoardDTO> search(String keyword) {
        return boardRepository.search(keyword);
    }

    public int totalPage() {
        return boardRepository.totalPage();
    }

    public int totalPageTap3(String userName) {
        return boardRepository.totalPageTap3(userName);
    }

    public List<CommentDTO> getComment(Long board_id) {
        return boardRepository.getComment(board_id);
    }

}
