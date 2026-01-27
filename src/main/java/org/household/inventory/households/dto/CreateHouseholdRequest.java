package org.household.inventory.households.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateHouseholdRequest {
  private String name;
  private String description;
}
