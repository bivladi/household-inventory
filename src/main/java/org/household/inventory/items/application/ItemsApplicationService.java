package org.household.inventory.items.application;

import java.util.List;
import org.household.inventory.model.Item;

public interface ItemsApplicationService {
  Item createItem(Item request);

  Item getItemById(String id);

  List<Item> getAllItems();

  Item updateItem(String id, Item updateRequest);
}
