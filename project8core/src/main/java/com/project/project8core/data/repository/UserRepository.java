package com.project.project8core.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.project8core.data.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User getByUid(String uid);
}
