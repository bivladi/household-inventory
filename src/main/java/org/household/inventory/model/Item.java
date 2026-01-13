package org.household.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item extends BaseEntity {
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "amount", nullable = false)
  private Integer amount;

  @Column(name = "price", nullable = false)
  // TODO change type for something more precise for currency
  private Double price;

  @ManyToMany
  @JoinTable(
      name = "item_categories",
      joinColumns = @JoinColumn(name = "item_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  List<Category> categories;
}
