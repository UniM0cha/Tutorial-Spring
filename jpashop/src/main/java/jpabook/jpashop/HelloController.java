package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
  
  @GetMapping("hello")
  public String hello(Model model){
    // data 라는 키로 Hello를 넘김
    model.addAttribute("data", "hello!!!");
    // 화면이름
    return "hello";
  }
}
