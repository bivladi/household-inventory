package org.household.inventory.households.application;

import java.util.UUID;
import org.household.inventory.households.dto.CreateItemRequest;
import org.household.inventory.model.Item;

public interface HouseholdAddItemApplicationService {
  Item createItem(UUID householdId, CreateItemRequest request);
}
