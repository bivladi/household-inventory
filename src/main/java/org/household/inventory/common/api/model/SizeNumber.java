package org.household.inventory.common.api.model;

public record SizeNumber(int value) {
  public SizeNumber(int value) {
    if (value <= 0) {
      throw new IllegalArgumentException("Size number must be greater than zero");
    }
    this.value = value;
  }
}
