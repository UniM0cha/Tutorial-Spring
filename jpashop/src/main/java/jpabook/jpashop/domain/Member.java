package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    // 내장타입을 사용했다.
    @Embedded
    private Address address;

    // Member : Order = 1 : N
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
