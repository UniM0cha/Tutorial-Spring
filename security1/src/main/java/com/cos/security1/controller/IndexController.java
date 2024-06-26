package com.cos.security1.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller // View를 리턴
@Slf4j
@RequiredArgsConstructor
public class IndexController {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @GetMapping("/test/login")
  public @ResponseBody String testLogin(
      Authentication authentication,
      @AuthenticationPrincipal PrincipalDetails userDetails) {
    System.out.println("/test/login ======================");
    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
    System.out.println("getUser : " + principalDetails.getUser());

    System.out.println("userDetails : " + userDetails);
    return "세션 정보 확인하기";
  }

  @GetMapping("/test/oauth/login")
  public @ResponseBody String testOAuthLogin(
      Authentication authentication,
      @AuthenticationPrincipal OAuth2User oauth) {
    System.out.println("/test/oauth/login ======================");
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
    System.out.println("authentication : " + oAuth2User.getAttributes());
    System.out.println("oauth2user : " + oauth.getAttributes());

    return "OAuth 세션 정보 확인하기";
  }

  @GetMapping({ "", "/" })
  public @ResponseBody String index() {
    // 머스테치 기본 폴더 : src/main/resources/
    // 뷰 리졸버 설정 : templates(prefix), .mustache(suffix) - 생략 가능
    return "index";
  }

  @GetMapping("/user")
  public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
    System.out.println("principalDetails : " + principalDetails);
    return "user";
  }

  @GetMapping("/admin")
  public @ResponseBody String admin() {
    return "admin";
  }

  @GetMapping("/manager")
  public @ResponseBody String manager() {
    return "manager";
  }

  // 스프링 시큐리티가 해당 주소를 낚아챔 -> SecurityConfig 파일 생성 후 낚아채지 않음.
  @GetMapping("/loginForm")
  public String loginForm() {
    return "loginForm";
  }

  @GetMapping("/joinForm")
  public String joinForm() {
    return "joinForm";
  }

  @PostMapping("/join")
  public String join(User user) {
    log.info(user.toString());
    user.setRole("ROLE_USER");
    String rawPassword = user.getPassword();
    String encPassword = bCryptPasswordEncoder.encode(rawPassword);
    user.setPassword(encPassword);
    userRepository.save(user);
    // 회원가입은 잘 되지만, 비밀번호가 그대로 들어가게 됨 = 시큐리티로 로그인을 할 수 없다. = 패스워드 암호화가 되지 않았기 때문.
    return "redirect:/loginForm";
  }

  // @Secured("ROLE_ADMIN")
  @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
  @GetMapping("/info")
  public @ResponseBody String info() {
    return "개인정보";
  }

}
