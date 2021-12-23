package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    // 열거형 정의하는데 기본값은 ORDINAL
    // 근데 이건 인덱스를 기준으로 값을 반환하므로 꼭 STRING 을 써주도록 하자
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; // READY, CAMP
}
