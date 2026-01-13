package org.household.inventory.items.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateItemRequest {
  private String name;
  private String description;
  private Integer amount;
  private Double price;
}
