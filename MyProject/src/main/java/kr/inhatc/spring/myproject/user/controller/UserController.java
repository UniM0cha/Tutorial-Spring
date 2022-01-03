package kr.inhatc.spring.myproject.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.inhatc.spring.myproject.user.entity.Users;
import kr.inhatc.spring.myproject.user.service.UserSerivce;

@Controller
public class UserController {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private UserSerivce userSerivce;

  @RequestMapping("/")
  public String hello() {

    return "redirect:/user/userList";
  }

  // RESTFUL!!!
  // GET(read), POST(create), PUT(update), DELETE(delete)
  @RequestMapping(value = "/user/userList", method = RequestMethod.GET)
  public String userList(Model model) {
    List<Users> list = userSerivce.userList();
    log.debug(list.toString());

    model.addAttribute("list", list);
    return "user/userList";
  }

}