package com.example.spring_io_mysql.repository;

import com.example.spring_io_mysql.domain.User;
import org.springframework.data.repository.CrudRepository;

// CrudRepository<객체, 키의 형식>
public interface UserRepository extends CrudRepository<User, Integer> {

}
