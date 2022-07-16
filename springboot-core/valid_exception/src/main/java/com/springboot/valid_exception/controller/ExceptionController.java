package com.springboot.valid_exception.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.valid_exception.common.Constants.ExceptionClass;
import com.springboot.valid_exception.common.exception.CustomException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/exception")
@Slf4j
public class ExceptionController {

  @GetMapping
  public void getRuntimeException() {
    throw new RuntimeException("getRuntimeException 메서드 호출");
  }

  @GetMapping("/custom")
  public void getCustomException() throws CustomException {
    throw new CustomException(ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "getCustomException 메서드 호출");
  }

  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity<Map<String, String>> handleException(RuntimeException e, HttpServletRequest request) {
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    log.error("클래스 내 handleException호출, {}, {}", request.getRequestURI(), e.getMessage());

    Map<String, String> map = new HashMap<>();
    map.put("error type", httpStatus.getReasonPhrase());
    map.put("code", "400");
    map.put("message", e.getMessage());

    return new ResponseEntity<>(map, responseHeaders, httpStatus);
  }
}
