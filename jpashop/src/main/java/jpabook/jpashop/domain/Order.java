package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // Order : Member = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    // 외래키 설정
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delibery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;  // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  // 주문상태 ORDER, CANCEL

}
