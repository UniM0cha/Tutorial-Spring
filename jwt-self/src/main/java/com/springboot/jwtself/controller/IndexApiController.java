package com.springboot.jwtself.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1")
public class IndexApiController {

  @PostMapping("/")
  public @ResponseBody String index() {
    return "index";
  }

  @PostMapping("/user")
  public @ResponseBody String user() {
    return "user";
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
