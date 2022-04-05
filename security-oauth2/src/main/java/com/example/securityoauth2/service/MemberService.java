package com.example.securityoauth2.service;

import com.example.securityoauth2.entity.MemberTO;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
  Integer save(MemberTO memberTO);
}
