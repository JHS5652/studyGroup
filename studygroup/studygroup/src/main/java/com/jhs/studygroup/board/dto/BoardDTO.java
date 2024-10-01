package com.jhs.studygroup.board.dto;

import lombok.Data;

@Data
public class BoardDTO {


    private Long id;
    private String userName;
    private String title;
    private String content;
    private int boardHits;//조회수
    private String createdAt;//만든 날짜
    private boolean status;
    private Long like_count;

}
