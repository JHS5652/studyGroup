package com.jhs.studygroup.board.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long board_id;
    private Long user_id;
    private String content;
    private String createdAt;
    private String userName;
}
