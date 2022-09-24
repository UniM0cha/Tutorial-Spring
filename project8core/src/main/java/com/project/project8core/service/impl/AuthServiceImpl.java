package com.project.project8core.service.impl;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.project8core.config.security.JwtTokenProvider;
import com.project.project8core.data.dto.SignInResultDto;
import com.project.project8core.data.dto.SignUpResultDto;
import com.project.project8core.data.entity.User;
import com.project.project8core.data.repository.UserRepository;
import com.project.project8core.service.AuthService;
import com.project.project8core.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;
  private final UserService userService;

  @Override
  public SignUpResultDto signUp(String uid, String password) {
    log.info("회원가입 정보 요청, id : {}", uid);

    // uid의 중복 확인
    userService.uidDuplicateCheck(uid);

    User newUser = User.builder()
        .uid(uid)
        .password(passwordEncoder.encode(password))
        .roles(Collections.singletonList("ROLE_USER"))
        .build();

    // 유저 저장
    User savedUser = userRepository.save(newUser);

    log.info("정상적으로 회원가입이 완료되었는지 확인");
    if (!savedUser.getUid().isEmpty()) {
      log.info("정상 처리 완료");
      return new SignUpResultDto(true, 0, "정상적으로 회원가입이 완료되었습니다.");
    } else {
      log.error("처리 중 에러 발생");
      throw new RuntimeException("회원가입 처리 중 에러가 발생했습니다.");
    }
  }

  @Override
  public SignInResultDto signIn(String uid, String password) throws RuntimeException {
    log.info("로그인 요청, id : {}", uid);
    User foundUser = userRepository.getByUid(uid);

    log.info("유저가 존재하는지 확인");
    if (foundUser == null) {
      throw new RuntimeException("해당 id의 유저가 존재하지 않습니다.");
    }

    log.info("패스워드 비교 수행");
    if (!passwordEncoder.matches(password, foundUser.getPassword())) {
      // 비밀번호가 일치하지 않으면 RuntimeException을 발생
      throw new RuntimeException("패스워드가 일치하지 않습니다.");
    }
    log.info("패스워드 일치");

    String token = jwtTokenProvider.generateToken(String.valueOf(foundUser.getUid()), foundUser.getRoles());
    SignInResultDto signInResultDto = SignInResultDto.builder()
        .token(token)
        .success(true)
        .code(0)
        .msg("정상적으로 로그인이 완료되었습니다.")
        .build();

    return signInResultDto;
  }
}
