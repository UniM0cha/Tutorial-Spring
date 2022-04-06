package jpabook.jpashop.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.Getter;

@Embeddable
@Getter
public class Address {
  private String city;
  private String street;
  private String zipcode;
}
