package org.household.inventory.common.api.model;

public record PageNumber(int value) {
  public PageNumber(int value) {
    if (value < 0) {
      throw new IllegalArgumentException("Page number cannot be negative");
    }
    this.value = value;
  }
}
