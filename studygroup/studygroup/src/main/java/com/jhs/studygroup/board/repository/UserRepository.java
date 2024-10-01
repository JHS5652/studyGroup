package com.jhs.studygroup.board.repository;

import com.jhs.studygroup.board.dto.BoardDTO;
import com.jhs.studygroup.board.dto.UserDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final SqlSessionTemplate sql;

    @Autowired
    public UserRepository(SqlSessionTemplate sql) {
        this.sql = sql;
    }


    public void join(UserDTO userDTO) {
        sql.insert("User.join", userDTO);
    }

    public UserDTO findByUser(UserDTO userDTO) {
        return sql.selectOne("User.findByUser",userDTO);
    }

    public UserDTO userInfo(String email) {
        return sql.selectOne("User.userInfo",email);
    }

    public void userInfoUpdate(UserDTO userDTO) {
        System.out.println(userDTO.toString());
        sql.update("User.userInfoUpdate", userDTO);
    }
}
