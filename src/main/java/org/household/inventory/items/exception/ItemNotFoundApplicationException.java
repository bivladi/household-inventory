package org.household.inventory.items.exception;

public class ItemNotFoundApplicationException extends RuntimeException {
  public ItemNotFoundApplicationException(String message) {
    super(message);
  }
}
