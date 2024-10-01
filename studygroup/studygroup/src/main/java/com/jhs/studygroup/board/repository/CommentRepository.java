package com.jhs.studygroup.board.repository;

import com.jhs.studygroup.board.dto.CommentDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {

    private final SqlSessionTemplate sql;

    @Autowired
    public CommentRepository(SqlSessionTemplate sql) {
        this.sql = sql;
    }

    public void commentSave(CommentDTO commentDTO) {
        sql.insert("Comment.commentSave",commentDTO);
    }

    public void commentDelete(Long id) {
        sql.delete("Comment.commentDelete",id);
    }
}
