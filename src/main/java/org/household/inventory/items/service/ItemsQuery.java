package org.household.inventory.items.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.household.inventory.model.Item;

public interface ItemsQuery {
  Optional<Item> findById(UUID id);

  List<Item> findAll();
}
