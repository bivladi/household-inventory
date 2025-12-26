package org.household.inventory.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Test controller. */
@RestController
public class HelloWorldController {

  /**
   * Dumb hello method.
   *
   * @return greeting string
   */
  @GetMapping("/hello")
  public ResponseEntity<String> helloWorld() {
    return ResponseEntity.ok("Hello, World!");
  }
}
