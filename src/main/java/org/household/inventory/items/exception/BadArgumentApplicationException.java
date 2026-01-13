package org.household.inventory.items.exception;

public class BadArgumentApplicationException extends RuntimeException {
  public BadArgumentApplicationException(String message) {
    super(message);
  }
}
