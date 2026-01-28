package org.household.inventory.households.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateItemRequest {
  private String categoryId;
  private String name;
  private String description;
  private Long amount;
  private Double price;
}
