package org.household.inventory.items.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.household.inventory.model.Item;
import org.springframework.data.domain.Page;

public interface FindItem {
  Optional<Item> findById(UUID id);

  boolean existsByName(String name);

  boolean isUnderCategory(UUID categoryId);

  List<Item> findAll();

  Page<Item> findAll(int page, int size, String sort, String direction);
}
