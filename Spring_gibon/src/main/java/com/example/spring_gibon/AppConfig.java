package com.example.spring_gibon;

import com.example.spring_gibon.policy.DiscountPolicy;
import com.example.spring_gibon.policy.RateDiscountPolicy;
import com.example.spring_gibon.repository.MemberRepository;
import com.example.spring_gibon.repository.MemoryMemberRepository;
import com.example.spring_gibon.service.OrderService;
import com.example.spring_gibon.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// AppConfig같은 애들을 IoC 컨테이너 혹은 DI 컨테이너라고 한다
@Configuration
public class AppConfig {
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
