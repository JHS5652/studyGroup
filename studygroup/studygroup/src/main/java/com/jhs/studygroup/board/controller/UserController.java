package com.jhs.studygroup.board.controller;
import com.jhs.studygroup.board.dto.UserDTO;
import com.jhs.studygroup.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String join(UserDTO userDTO, Model model) {
        boolean b = false;
        if (userService.join(userDTO) == true) {
            userService.join(userDTO);

            return "redirect:/login";
        } else {
            b = true;
            model.addAttribute("error", b);
            return "join";
        }

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(UserDTO userDTO, HttpSession session) {
        UserDTO user = userService.userExists(userDTO);
        if (user != null) {
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userId", user.getId());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("password", user.getPassword());
            System.out.println(user.getId());
            return "redirect:/main";
        } else {
            return "login";
        }

    }

    @GetMapping("/userPage")
    public String userPage(HttpSession session, Model model) {
        //        회원 아이디
        String email = (String) session.getAttribute("email");
        model.addAttribute("email", email);

        //        회원 이름
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);



        UserDTO userDTO = userService.userInfo(email);
        model.addAttribute("user", userDTO);

        model.addAttribute("passwordCheck",1);

        return "userPage";
    }

    @PostMapping("/userPage")
    public String userPage(@RequestParam("passwordCheck") String passwordCheck, UserDTO userDTO, HttpSession session, Model model) {
        //        회원 아이디
        String email = (String) session.getAttribute("email");
        model.addAttribute("email", email);

        //        회원 이름
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);

        //        회원 비밀번호
        String password = (String) session.getAttribute("password");
        model.addAttribute("password", password);

        String ps = passwordCheck.trim();
        System.out.println("회원의 비번"+password);
        System.out.println("입력한 비번"+ps);
        if(ps != null && !ps.isEmpty()){
            if (password.equals(ps)){
                model.addAttribute("passwordCheck",1);
                System.out.println("들어옴");
            }else {
                model.addAttribute("passwordCheck",0);

                UserDTO user = userService.userInfo(email);
                model.addAttribute("user", user);
                return "userPage";
            }
        }

        userDTO.setEmail(email);
        userService.userInfoUpdate(userDTO);

        return "redirect:/userPage";
    }
}
