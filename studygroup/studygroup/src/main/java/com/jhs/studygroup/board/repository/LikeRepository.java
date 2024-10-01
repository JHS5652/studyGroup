package com.jhs.studygroup.board.repository;

import com.jhs.studygroup.board.dto.LikeDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository
public class LikeRepository {
    private final SqlSessionTemplate sql;

    @Autowired
    public LikeRepository(SqlSessionTemplate sql) {
        this.sql = sql;
    }


    public void plus(LikeDTO likeDTO) {
        sql.insert("Like.plus", likeDTO);
    }

    public void minus(LikeDTO likeDTO) {
        sql.delete("Like.delete",likeDTO);
    }

    public void boardLike(Long id) {
        sql.update("Like.boardLike", id);
    }

    public void boardDisLike(Long id) {
        sql.update("Like.boardDisLike", id);
    }
}
