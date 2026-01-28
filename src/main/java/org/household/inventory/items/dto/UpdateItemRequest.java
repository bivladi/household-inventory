package org.household.inventory.items.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for updating an existing item. All fields are nullable to support partial updates. Null
 * fields will be excluded from the update operation.
 */
@Getter
@Setter
@AllArgsConstructor
public class UpdateItemRequest {
  private String name;
  private String description;
  private Double price;
}
