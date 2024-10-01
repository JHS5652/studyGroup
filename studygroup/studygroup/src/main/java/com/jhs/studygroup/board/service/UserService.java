package com.jhs.studygroup.board.service;

import com.jhs.studygroup.board.dto.UserDTO;
import com.jhs.studygroup.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean join(UserDTO userDTO) {


        UserDTO foundUser = userRepository.findByUser(userDTO);

        if(foundUser == null) {
            System.out.println("회원 가입 성공");
            userRepository.join(userDTO);
            return true;
        }else{
            System.out.println("회원 가입 실패");
            return false;
        }
    }

    public UserDTO userExists(UserDTO userDTO) {
        UserDTO foundUser = userRepository.findByUser(userDTO);

        if (foundUser != null) {
            if (userDTO.getEmail().equals(foundUser.getEmail()) &&
                    userDTO.getPassword().equals(foundUser.getPassword())) {
                System.out.println("로그인 성공");
                return foundUser;
            } else {
                System.out.println("로그인 실패");
                return null;
            }
        }else {
            System.out.println("사용자를 찾을 수 없습니다");
        }
        return null;
    }

    public UserDTO userInfo(String email) {
        return userRepository.userInfo(email);
    }

    public void userInfoUpdate(UserDTO userDTO) {
        userRepository.userInfoUpdate(userDTO);
    }
}
