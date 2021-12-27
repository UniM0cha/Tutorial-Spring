package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

// 내장타입이라는 것을 알려준다
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // Setter는 제공하지 않고 생성자로만 생성해서 다시는 수정할 수 없도록 만든다.
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    // JPA는 기본 생성자가 없으면 안된다..
    protected Address() {}
}
