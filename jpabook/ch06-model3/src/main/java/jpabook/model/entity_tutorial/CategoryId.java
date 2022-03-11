package jpabook.model.entity_tutorial;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CategoryId {
  @Id
  @ManyToOne
  private Category category;

  @Id
  @ManyToOne
  private Item item;

  public Category getCategory() {
    return this.category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Item getItem() {
    return this.item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

}