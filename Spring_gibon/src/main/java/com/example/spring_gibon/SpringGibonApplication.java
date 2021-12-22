package com.example.spring_gibon;

import com.example.spring_gibon.domain.Grade;
import com.example.spring_gibon.domain.Member;
import com.example.spring_gibon.domain.Order;
import com.example.spring_gibon.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringGibonApplication {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);
        Order order = orderService.createOrder(memberId, "itemA", 20000);

        SpringApplication.run(SpringGibonApplication.class, args);
    }

}
