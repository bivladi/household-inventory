package org.household.inventory.items.application.impl;

import java.util.List;
import java.util.UUID;
import org.household.inventory.items.application.ItemsApplicationService;
import org.household.inventory.items.exception.BadArgumentApplicationException;
import org.household.inventory.items.exception.ItemNotFoundApplicationException;
import org.household.inventory.items.repository.ItemsRepository;
import org.household.inventory.items.service.ItemsQuery;
import org.household.inventory.model.Item;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ItemsApplicationServiceImpl implements ItemsApplicationService {

  private final ItemsQuery itemsQuery;
  private final ItemsRepository repository;

  public ItemsApplicationServiceImpl(ItemsQuery itemsQuery, ItemsRepository repository) {
    this.itemsQuery = itemsQuery;
    this.repository = repository;
  }

  @Override
  public Item createItem(Item item) {
    return repository.save(item);
  }

  @Override
  public Item getItemById(String id) {
    if (!StringUtils.hasText(id)) {
      throw new BadArgumentApplicationException("item id cannot be null or empty");
    }
    return itemsQuery
        .findById(UUID.fromString(id))
        .orElseThrow(() -> new ItemNotFoundApplicationException("item not found with id: " + id));
  }

  @Override
  public List<Item> getAllItems() {
    return itemsQuery.findAll();
  }
}
