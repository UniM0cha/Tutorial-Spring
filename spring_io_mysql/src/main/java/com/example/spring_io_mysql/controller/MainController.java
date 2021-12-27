package com.example.spring_io_mysql.controller;

import com.example.spring_io_mysql.domain.User;
import com.example.spring_io_mysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

    // 스프링이 등록해준 userRepository라는 이름의 빈을 알아서 가져온다
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    // 스트링 그대로를 클라이언트에게 쏴준다. 뷰가 아니라.
    @ResponseBody
    // RequestParam은 Get이나 Post에서 파라미터를 받는다. (Get의 경우엔 ?뒤에 있는 값들
    public String addNewUser(@RequestParam String name, @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping("/all")
    @ResponseBody
    public Iterable<User> getAllUsers() {
        // 클라이언트에게 Json 형태로 전송한다
        return userRepository.findAll();
    }
}
