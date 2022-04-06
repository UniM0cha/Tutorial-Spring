package jpabook.jpashop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;

@SpringBootTest
public class MemberRepositoryTests {

  @Autowired
  MemberRepository memberRepository;

  @Test
  @Transactional
  void testMember() {
    Member member = new Member();
    member.setUsername("memberA");

    Long savedId = memberRepository.save(member);
    Member findMember = memberRepository.find(savedId);

    assertEquals(findMember.getId(), member.getId());
    assertEquals(findMember.getUsername(), member.getUsername());
    assertEquals(findMember, member);
  }

}
