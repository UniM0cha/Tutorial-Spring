package jpabook.model.entity_tutorial;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Member {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String name;

  @Column
  private String city;

  @Column
  private String street;

  @Column
  private String zipcode;

  @OneToMany(mappedBy = "member")
  private List<Order> orders = new ArrayList<Order>();

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return this.street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getZipcode() {
    return this.zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public List<Order> getOrders() {
    return this.orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

}