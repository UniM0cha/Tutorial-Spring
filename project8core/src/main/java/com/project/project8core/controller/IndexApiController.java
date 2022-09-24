package com.project.project8core.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.project8core.data.entity.User;

@Controller
@RequestMapping("/api/v1")
public class IndexApiController {

  @PostMapping("/")
  public @ResponseBody String index() {
    return "index";
  }

  @PostMapping("/user")
  public @ResponseBody String user(@AuthenticationPrincipal User user) {
    return user.getUid() + user.getPassword();
  }

  @PostMapping("/manager")
  public @ResponseBody String manager() {
    return "manager";
  }

  @PostMapping("/admin")
  public @ResponseBody String admin() {
    return "admin";
  }
}
