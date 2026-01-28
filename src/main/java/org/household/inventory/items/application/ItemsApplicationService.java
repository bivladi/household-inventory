package org.household.inventory.items.application;

import java.util.UUID;
import org.household.inventory.model.Item;
import org.springframework.data.domain.Page;

public interface ItemsApplicationService {
  Item createItem(Item request);

  Item getItemById(UUID id);

  Page<Item> getAllItems(int page, int size, String sort, String direction);

  Item updateItem(UUID id, Item updateRequest);

  void deleteItem(UUID id);
}
