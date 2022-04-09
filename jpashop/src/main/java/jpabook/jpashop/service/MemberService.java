package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.domain.Member;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
// @AllArgsConstructor
// final 로 선언한 필드만 생성자로 등록해준다.
@RequiredArgsConstructor
@Transactional(readOnly = true)
// JPA의 모든 데이터 변경은 가급적 트랜잭션 안에서 수행!, javax보다 스프링에서 제공하는 트랜잭선 사용하기
// 읽기, 조회에는 readOnly 옵션을 넣어주면 성능 최적화된다.
public class MemberService {

    // 파이널로 해놓으면 변경될 수 없기 때문에 좋다
    private final MemberRepository memberRepository;

    // 회원가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
