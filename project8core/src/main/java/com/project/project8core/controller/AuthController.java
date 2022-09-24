package com.project.project8core.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.project8core.data.dto.SignInRequestDto;
import com.project.project8core.data.dto.SignInResultDto;
import com.project.project8core.data.dto.SignUpRequestDto;
import com.project.project8core.data.dto.SignUpResultDto;
import com.project.project8core.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
  private final AuthService signService;

  @PostMapping(value = "/sign-up")
  public SignUpResultDto signUp(@RequestBody SignUpRequestDto signUpRequestDto)
      throws RuntimeException {
    log.info("회원가입을 수행합니다. id : {}, pw : ****", signUpRequestDto.getId());
    SignUpResultDto signUpResultDto = signService.signUp(signUpRequestDto.getId(), signUpRequestDto.getPassword());
    return signUpResultDto;
  }

  @PostMapping(value = "/sign-in")
  public SignInResultDto signIn(@RequestBody SignInRequestDto signInRequestDto) throws RuntimeException {
    log.info("로그인을 시도하고 있습니다. id : {}, pw : ****", signInRequestDto.getId());
    SignInResultDto signInResultDto = signService.signIn(signInRequestDto.getId(), signInRequestDto.getPassword());

    if (signInResultDto.getCode() == 0) {
      log.info("정상적으로 로그인되었습니다. id : {}, token : {}", signInRequestDto.getId(),
          signInResultDto.getToken());
    }

    return signInResultDto;
  }

  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
    HttpHeaders responHeaders = new HttpHeaders();
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    log.error("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

    Map<String, String> map = new HashMap<>();
    map.put("error type", httpStatus.getReasonPhrase());
    map.put("code", "400");
    map.put("message", e.getMessage());

    return new ResponseEntity<>(map, responHeaders, httpStatus);
  }
}
