package org.household.inventory.common.exception;

import java.io.Serial;

public class NotFoundApplicationException extends RuntimeException {
  @Serial private static final long serialVersionUID = 8662736712682058268L;

  public NotFoundApplicationException(String message) {
    super(message);
  }
}
