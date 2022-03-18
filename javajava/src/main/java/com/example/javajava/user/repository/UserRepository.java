package com.example.javajava.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.javajava.user.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

  // JPA 쿼리메소드 라고 검색해보자
  // https://happygrammer.tistory.com/158
  List<Users> findAllByOrderByIdDesc();

}
