package com.example.jpabookplus.domain;

import org.springframework.data.jpa.domain.Specification;

import static com.example.jpabookplus.domain.OrderSpec.memberNameLike;
import static com.example.jpabookplus.domain.OrderSpec.orderStatusEq;;

/**
 * Created by holyeye on 2014. 3. 15..
 */
public class OrderSearch {

    private String memberName; // 회원 이름
    private OrderStatus orderStatus;// 주문 상태

    // Getter, Setter
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Specification<Order> toSpecification() {
        return Specification.where(memberNameLike(memberName)).and(orderStatusEq(orderStatus));
    }
}
