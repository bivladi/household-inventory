package org.household.inventory.items.exception;

import java.io.Serial;

public class ItemNotFoundApplicationException extends RuntimeException {
  @Serial private static final long serialVersionUID = 8662736712682058268L;

  public ItemNotFoundApplicationException(String message) {
    super(message);
  }
}
