package jpabook.model.entity_tutorial;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Item {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String name;

  @OneToMany(mappedBy = "items")
  private List<CategoryId> categoryIds = new ArrayList<CategoryId>();

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

  public List<CategoryId> getCategoryIds() {
    return this.categoryIds;
  }

  public void setCategoryIds(List<CategoryId> categoryIds) {
    this.categoryIds = categoryIds;
  }

}