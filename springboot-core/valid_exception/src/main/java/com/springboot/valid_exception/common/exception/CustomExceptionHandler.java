package com.springboot.valid_exception.common.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity<Map<String, String>> handleException(RuntimeException e, HttpServletRequest request) {
    HttpHeaders responseHeaders = new HttpHeaders();
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    log.error("Advice 내 handleException호출, {}, {}", request.getRequestURI(), e.getMessage());

    Map<String, String> map = new HashMap<>();
    map.put("error type", httpStatus.getReasonPhrase());
    map.put("code", "400");
    map.put("message", e.getMessage());

    return new ResponseEntity<>(map, responseHeaders, httpStatus);
  }

  @ExceptionHandler(value = CustomException.class)
  public ResponseEntity<Map<String, String>> handleException2(CustomException e, HttpServletRequest request) {
    HttpHeaders responseHeaders = new HttpHeaders();

    log.error("Advice 내 handleException호출, {}, {}", request.getRequestURI(), e.getMessage());

    Map<String, String> map = new HashMap<>();
    map.put("error type", Integer.toString(e.getHttpStatusCode()));
    map.put("code", "400");
    map.put("message", e.getMessage());

    return new ResponseEntity<>(map, responseHeaders, e.getHttpStatus());
  }
}
