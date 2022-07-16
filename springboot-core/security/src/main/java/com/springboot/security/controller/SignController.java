package com.springboot.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.data.dto.SignInResultDto;
import com.springboot.security.data.dto.SignUpResultDto;
import com.springboot.security.service.SignService;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sign-api")
@RequiredArgsConstructor
@Slf4j
public class SignController {
  private final SignService signService;

  @PostMapping(value = "/sign-in")
  public SignInResultDto signIn(
      @Parameter(description = "ID", required = true) @RequestParam String id,
      @Parameter(description = "Password", required = true) @RequestParam String password) throws RuntimeException {
    log.info("[signIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", id);
    SignInResultDto signInResultDto = signService.signIn(id, password);

    if (signInResultDto.getCode() == 0) {
      log.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token ; {}", id, signInResultDto.getToken());
    }

    return signInResultDto;
  }

  @PostMapping(value = "/sign-up")
  public SignUpResultDto signUp(
      @Parameter(description = "ID", required = true) @RequestParam String id,
      @Parameter(description = "비밀번호", required = true) @RequestParam String password,
      @Parameter(description = "이름", required = true) @RequestParam String name,
      @Parameter(description = "권한", required = true) @RequestParam String role) throws RuntimeException {
    log.info("[signUp] 회원가입을 수행합니다. id : {}, pw : ****, name : {}, role : {}", id, name, role);
    SignUpResultDto signUpResultDto = signService.signUp(id, password, name, id);

    log.info("[signUp] 회원가입을 완료했습니다. id : {}", id);

    return signUpResultDto;
  }

  @GetMapping(value = "/exception")
  public void exceptionTest() throws RuntimeException {
    throw new RuntimeException("접근이 금지되었습니다.");
  }

  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
    HttpHeaders responHeaders = new HttpHeaders();
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    log.error("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

    Map<String, String> map = new HashMap<>();
    map.put("error type", httpStatus.getReasonPhrase());
    map.put("code", "400");
    map.put("message", "에러 발생");

    return new ResponseEntity<>(map, responHeaders, httpStatus);
  }

}
