package com.example.spring_gibon;

import com.example.spring_gibon.service.OrderService;
import com.example.spring_gibon.service.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        OrderService orderService = ac.getBean("orderService", OrderService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderServiceImpl.class);
    }
}
