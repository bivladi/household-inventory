package org.household.inventory.categories.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateCategoryRequest {
  private String name;
  private String description;
}
