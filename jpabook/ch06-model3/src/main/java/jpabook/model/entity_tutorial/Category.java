package jpabook.model.entity_tutorial;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String name;

  @Column
  private int price;

  @Column
  private int stockQuantity;

  @OneToMany(mappedBy = "category")
  private List<CategoryId> categorys = new ArrayList<CategoryId>();

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

  public int getPrice() {
    return this.price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getStockQuantity() {
    return this.stockQuantity;
  }

  public void setStockQuantity(int stockQuantity) {
    this.stockQuantity = stockQuantity;
  }

  public List<CategoryId> getCategorys() {
    return this.categorys;
  }

  public void setCategorys(List<CategoryId> categorys) {
    this.categorys = categorys;
  }

  public void addCategory(CategoryId category) {
    this.categorys.add(category);
  }

}