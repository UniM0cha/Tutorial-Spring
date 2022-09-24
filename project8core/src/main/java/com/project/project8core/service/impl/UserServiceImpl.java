package com.project.project8core.service.impl;

import org.springframework.stereotype.Service;

import com.project.project8core.data.entity.User;
import com.project.project8core.data.repository.UserRepository;
import com.project.project8core.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public boolean uidDuplicateCheck(String uid) {
    log.info("uid가 중복인지 확인");
    User foundUser = userRepository.getByUid(uid);
    if (foundUser == null) {
      log.info("uid 중복 확인 결과 : 중복이 아닙니다.");
      return true;
    } else {
      throw new RuntimeException("중복된 아이디입니다.");
    }
  }

}
