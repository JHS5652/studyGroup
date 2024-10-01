package com.jhs.studygroup.board.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String userName;
    private String phoneNumber;
}
