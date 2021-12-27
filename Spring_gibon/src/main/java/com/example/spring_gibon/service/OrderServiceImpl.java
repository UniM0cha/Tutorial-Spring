package com.example.spring_gibon.service;

import com.example.spring_gibon.domain.Order;
import com.example.spring_gibon.policy.DiscountPolicy;
import com.example.spring_gibon.repository.MemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long id, String name, Integer price) {
        Order order = new Order();
        return order;
    }
}
