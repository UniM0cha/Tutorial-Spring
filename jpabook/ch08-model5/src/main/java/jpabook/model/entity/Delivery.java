package jpabook.model.entity;

import javax.persistence.*;

/**
 * Created by holyeye on 2014. 3. 11..
 */
@Entity
public class Delivery {

  @Id
  @GeneratedValue
  @Column(name = "DELIVERY_ID")
  private Long id;

  @OneToOne(mappedBy = "delivery")
  private Order order;

  @Embedded
  private Address address;

  @Enumerated(EnumType.STRING)
  private DeliveryStatus status; // ENUM [READY(준비), COMP(배송)]

  // Getter, Setter
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Address getAddress() {
    return this.address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public DeliveryStatus getStatus() {
    return this.status;
  }

  public void setStatus(DeliveryStatus status) {
    this.status = status;
  }

}