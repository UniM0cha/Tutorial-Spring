package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    // 바디에다 그대로 넘겨주겠다
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;     // hello spring
        // 문자열을 리턴한 것
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // 클래스(객체)를 리턴하면 json의 형태로 날라가게 된다
        // json은 name : value 처럼 간단한 형태. 요즘엔 거의다 json 형태로 쓴다 (옛날엔 XML 형태였음(태그형태))
    }

    // 자바 빈 표준 방식 = 게터세터 = 프로퍼티 접근방식
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
