package kr.inhatc.spring.myproject.user.service;

import java.util.List;

import kr.inhatc.spring.myproject.user.entity.Users;

public interface UserService {

  List<Users> userList();

  void saveUsers(Users user);

  Users userDetail(String id);

  void deleteUsers(String id);

}