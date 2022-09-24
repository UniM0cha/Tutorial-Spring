package com.project.project8core.service;

import com.project.project8core.data.dto.SignInResultDto;
import com.project.project8core.data.dto.SignUpResultDto;

public interface AuthService {
  SignUpResultDto signUp(String uid, String password);

  SignInResultDto signIn(String uid, String password) throws RuntimeException;
}
