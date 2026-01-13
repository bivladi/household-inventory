package org.household.inventory.items.application;

import org.household.inventory.model.Item;

import java.util.List;

public interface ItemsApplicationService {
  Item createItem(Item request);

  Item getItemById(String id);

  List<Item> getAllItems();
}
