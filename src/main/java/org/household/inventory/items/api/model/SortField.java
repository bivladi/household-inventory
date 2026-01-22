package org.household.inventory.items.api.model;

import lombok.Getter;

@Getter
public enum SortField {
  CREATED_AT("createdAt"),
  UPDATED_AT("updatedAt"),
  NAME("name");

  private final String value;

  SortField(String value) {
    this.value = value;
  }
}
