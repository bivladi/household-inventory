package org.household.inventory.common.api.model;

import lombok.Getter;

@Getter
public enum SortDirection {
  ASC("asc"),
  DESC("desc");

  private final String value;

  SortDirection(String value) {
    this.value = value;
  }
}
