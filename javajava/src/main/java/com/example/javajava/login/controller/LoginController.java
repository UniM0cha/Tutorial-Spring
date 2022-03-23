package com.example.javajava.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
  @GetMapping("/login/login")
  public String login() {
    return "login/login";
  }

  @GetMapping("/login/accessDenied")
  public void accessDenied() {
    // 선언을 안해도 알아서 찾아간다.
  }

  @GetMapping("/login/logout")
  public void logout() {
    log.info("==================> 로그아웃");
  }
}