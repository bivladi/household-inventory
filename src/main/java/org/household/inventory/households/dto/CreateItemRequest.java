package org.household.inventory.households.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateItemRequest {
  private UUID categoryId;
  private String name;
  private String description;
  private Long amount;
  private Double price;
}
