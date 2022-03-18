package com.example.javajava.user.service;

import java.util.List;

import com.example.javajava.user.entity.Users;

public interface UserService {

  List<Users> userList();

  void saveUsers(Users user);

  Users userDetail(String id);

  void deleteUsers(String id);

}