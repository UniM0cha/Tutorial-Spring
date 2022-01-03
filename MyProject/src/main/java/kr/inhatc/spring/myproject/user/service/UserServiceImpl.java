package kr.inhatc.spring.myproject.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.myproject.user.entity.Users;
import kr.inhatc.spring.myproject.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserSerivce {

  @Autowired
  UserRepository userRepository;

  @Override
  public List<Users> userList() {
    List<Users> list = userRepository.findAllByOrderByIdDesc();
    return list;
  }

}
