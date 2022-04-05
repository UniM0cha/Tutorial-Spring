package com.example.securityoauth2.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.example.securityoauth2.entity.Member;
import com.example.securityoauth2.entity.MemberTO;
import com.example.securityoauth2.repository.MembeRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

  private MembeRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
    Member member = memberRepository.findByAccount(account).orElse(null);

    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

    // spring security에서 제공하는 User 클래스를 반환한다
    // username과 password, 권한(리스트)를 넣어서 생성해준다.
    return new User(member.getAccount(), member.getPassword(), authorities);
  }

  // 회원정보를 저장하는 메소드.
  // 시간 데이터에 현재 시간을 넣고 암호를 암호화한다.
  @Transactional
  @Override
  public Integer save(MemberTO memberTO) {
    Member member = memberTO.toEntity();
    member.setLastAccessDt(LocalDateTime.now());
    member.setRegDt(LocalDateTime.now());

    // 비밀번호 암호화
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    member.setPassword(passwordEncoder.encode(member.getPassword()));
    return memberRepository.save(member).getId();
  }

}
