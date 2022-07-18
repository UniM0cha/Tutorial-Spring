package com.springboot.jwtself.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommonResponse {
  SUCCESS(0, "Success"), FAIL(-1, "Fail");

  int code;
  String msg;
}
