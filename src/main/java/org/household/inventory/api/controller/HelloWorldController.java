package org.household.inventory.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/hello/{name}")
  public ResponseEntity<String> hello(@PathVariable("name") String name) {
    return ResponseEntity.ok("Hello, " + name + "!");
  }
}
