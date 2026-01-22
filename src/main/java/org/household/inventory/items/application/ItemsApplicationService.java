package org.household.inventory.items.application;

import java.util.List;
import org.household.inventory.model.Item;
import org.springframework.data.domain.Page;

public interface ItemsApplicationService {
  Item createItem(Item request);

  Item getItemById(String id);

  List<Item> getAllItems();

  Page<Item> getAllItems(int page, int size, String sort, String direction);

  Item updateItem(String id, Item updateRequest);
}
