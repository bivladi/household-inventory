package org.household.inventory.households.application;

import org.household.inventory.households.dto.CreateItemRequest;
import org.household.inventory.model.Item;

public interface HouseholdAddItemApplicationService {
  Item createItem(String householdId, CreateItemRequest request);
}
