package com.springboot.jwtself.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jwtself.data.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User getByUid(String uid);
}
