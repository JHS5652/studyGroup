package com.jhs.studygroup.board.repository;

import com.jhs.studygroup.board.dto.BoardDTO;
import com.jhs.studygroup.board.dto.CommentDTO;
import com.jhs.studygroup.board.dto.LikeDTO;
import com.jhs.studygroup.board.dto.UserDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {
    private final SqlSessionTemplate sql;

    @Autowired
    public BoardRepository(SqlSessionTemplate sql) {
        this.sql = sql;
    }//생성자 주입

    public void save(BoardDTO boardDTO) {
        sql.insert("Board.save", boardDTO);
    }

    public List<BoardDTO> findAll(String keyword, int limit, int offset) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        params.put("offset", offset);
        params.put("keyword", keyword);

        return sql.selectList("Board.findAll", params);
    }


    public List<BoardDTO> getRecruitingBoards() {
        return sql.selectList("Board.getRecruitingBoards");
    }

    public List<BoardDTO> closeRecruitingBoards() {
        return sql.selectList("Board.closeRecruitingBoards");
    }

    public List<BoardDTO> myBoard(String userName, int limit, int offset) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        params.put("offset", offset);
        params.put("userName", userName);
        return sql.selectList("Board.myBoard",params);
    }

    public void updateHits(Long id) {
        sql.update("Board.updateHits",id);
    }

    public BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById", id);
    }


    public void update(BoardDTO boardDTO) {
        sql.update("Board.update",boardDTO);
    }

    public void delete(Long id) {
        sql.delete("Board.delete", id);
    }

    public List<LikeDTO> getLikeCount(Long id) {
        return sql.selectList("Board.getLikeCount", id);
    }

    public boolean isLikedByUser(LikeDTO likeDTO) {
        return sql.selectOne("Board.isLikedByUser", likeDTO);
    }

    public List<BoardDTO> findMostLike(String keyword, int limit, int offset) {

        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        params.put("offset", offset);
        params.put("keyword", keyword);

        return sql.selectList("Board.findMostLike",params);
    }

    public List<BoardDTO> search(String keyword) {
        return sql.selectList("Board.search",keyword);
    }

    public int totalPage() {
        return sql.selectOne("Board.totalPage");
    }

    public int totalPageTap3(String userName) {
        return sql.selectOne("Board.totalPageTap3",userName);
    }

    public List<CommentDTO> getComment(Long board_id) {
        return sql.selectList("Comment.getComment",board_id);
    }

}
