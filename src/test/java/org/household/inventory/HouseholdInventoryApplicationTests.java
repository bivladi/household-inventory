package org.household.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

// TODO introduce keycloak and db dependencies for tests
@SpringBootTest
@ActiveProfiles("test")
class HouseholdInventoryApplicationTests {

  @Test
  void contextLoads() {
    assertDoesNotThrow(() -> true);
  }
}
