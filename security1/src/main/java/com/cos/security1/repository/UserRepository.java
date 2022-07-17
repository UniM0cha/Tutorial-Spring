package com.cos.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security1.model.User;

// 기본적인 CRUD 함수를 JpaRepository가 가지고 있다.
// @Repository를 붙히지 않아도 JpaRepository를 상속받았기 때문에 IoC가 된다.
public interface UserRepository extends JpaRepository<User, Integer> {
  /**
   * findBy규칙 -> Username 문법
   * select * from user where username = 1?
   */
  User findByUsername(String username);
}
