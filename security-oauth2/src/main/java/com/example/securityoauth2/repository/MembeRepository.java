package com.example.securityoauth2.repository;

import java.util.Optional;

import com.example.securityoauth2.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MembeRepository extends JpaRepository<Member, Integer> {
  Optional<Member> findByAccount(String account);
}
