package com.springboot.jwtself.service;

import com.springboot.jwtself.data.dto.SignInResultDto;
import com.springboot.jwtself.data.dto.SignUpResultDto;

public interface SignService {
  SignUpResultDto signUp(String id, String password, String name, String role);

  SignInResultDto signIn(String id, String password) throws RuntimeException;
}
