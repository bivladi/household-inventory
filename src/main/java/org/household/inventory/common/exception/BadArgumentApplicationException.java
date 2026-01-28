package org.household.inventory.common.exception;

import java.io.Serial;

public class BadArgumentApplicationException extends RuntimeException {
  @Serial private static final long serialVersionUID = 5471951727547945525L;

  public BadArgumentApplicationException(String message) {
    super(message);
  }
}
