package com.springboot.valid_exception.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.valid_exception.data.dto.ValidRequestDto;
import com.springboot.valid_exception.data.dto.ValidatedRequestDto;
import com.springboot.valid_exception.data.group.ValidationGroup1;
import com.springboot.valid_exception.data.group.ValidationGroup2;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/validation")
@Slf4j
public class ValidationController {

  @PostMapping("/validated")
  public ResponseEntity<String> checkValidationByValid(@Valid @RequestBody ValidRequestDto validRequestDto) {
    log.info(validRequestDto.toString());
    return ResponseEntity.status(HttpStatus.OK).body(validRequestDto.toString());
  }

  @PostMapping("validated/group1")
  public ResponseEntity<String> checkValidation1(
      @Validated(ValidationGroup1.class) @RequestBody ValidatedRequestDto validatedRequestDto) {
    log.info(validatedRequestDto.toString());
    return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
  }

  @PostMapping("validated/group2")
  public ResponseEntity<String> checkValidation2(
      @Validated(ValidationGroup2.class) @RequestBody ValidatedRequestDto validatedRequestDto) {
    log.info(validatedRequestDto.toString());
    return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
  }

  @PostMapping("validated/all-group")
  public ResponseEntity<String> checkValidation3(
      @Validated({ ValidationGroup1.class,
          ValidationGroup2.class }) @RequestBody ValidatedRequestDto validatedRequestDto) {
    log.info(validatedRequestDto.toString());
    return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
  }
}
