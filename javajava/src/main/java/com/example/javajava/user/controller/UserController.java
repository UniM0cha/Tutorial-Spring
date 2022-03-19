package com.example.javajava.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.javajava.user.entity.Users;
import com.example.javajava.user.service.UserService;

@Controller
public class UserController {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private UserService userSerivce;

  @RequestMapping("/")
  public String home() {
    return "index";
  }

  // RESTFUL!!!
  // GET(read), POST(create), PUT(update), DELETE(delete)
  @RequestMapping(value = "/user/userList", method = RequestMethod.GET)
  public String userList(Model model) {
    List<Users> list = userSerivce.userList();
    log.debug(list.toString());

    model.addAttribute("list", list);
    return "/user/userList";
  }

  @RequestMapping(value = "/user/userInsert", method = RequestMethod.GET)
  public String userWrite(Model model) {
    List<String> enabledList = new ArrayList<>();
    enabledList.add("가능");
    enabledList.add("불가능");

    List<String> authorityList = new ArrayList<>();
    authorityList.add("ROLE_GUEST");
    authorityList.add("ROLE_MEMBER");
    authorityList.add("ROLE_ADMIN");

    Map<String, List<String>> map = new HashMap<>();
    map.put("enabledList", enabledList);
    map.put("authorityList", authorityList);

    model.addAttribute("map", map);

    return "/user/userWrite";
  }

  @RequestMapping(value = "/user/userInsert", method = RequestMethod.POST)
  public String userInsert(Users user) {
    userSerivce.saveUsers(user);
    return "redirect:/user/userList";
  }

  @RequestMapping(value = "/user/userDetail/{id}", method = RequestMethod.GET)
  public String userDetail(@PathVariable("id") String id, Model model) {
    Users user = userSerivce.userDetail(id);
    model.addAttribute("user", user);
    log.debug("===============================>", user);
    return "/user/userDetail";
  }

  @RequestMapping(value = "/user/userUpdate", method = RequestMethod.POST)
  public String userUpdate(Users user) {
    log.debug("===============================>" + user);
    userSerivce.saveUsers(user);
    return "redirect:/user/userList";
  }

  @RequestMapping(value = "/user/userDelete/{id}", method = RequestMethod.GET)
  public String userDelete(@PathVariable("id") String id) {
    userSerivce.deleteUsers(id);
    return "redirect:/user/userList";
  }

}