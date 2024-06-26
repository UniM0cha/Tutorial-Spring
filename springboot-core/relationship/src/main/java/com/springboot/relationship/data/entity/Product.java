package com.springboot.relationship.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long number;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Integer price;

  @Column(nullable = false)
  private Integer stock;

  @OneToOne(mappedBy = "product")
  @ToString.Exclude
  private ProductDetail productDetail;

  @ManyToOne
  @JoinColumn(name = "provider_id")
  @ToString.Exclude
  private Provider provider;

  @ManyToMany
  @ToString.Exclude
  private List<Producer> producers = new ArrayList<>();

  public void addProducer(Producer producer) {
    this.producers.add(producer);
  }
}
