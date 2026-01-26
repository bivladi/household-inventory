package org.household.inventory.items.application;

import org.household.inventory.model.Item;
import org.springframework.data.domain.Page;

public interface ItemsApplicationService {
  Item createItem(Item request);

  Item getItemById(String id);

  Page<Item> getAllItems(int page, int size, String sort, String direction);

  Item updateItem(String id, Item updateRequest);

  void deleteItem(String id);
}
